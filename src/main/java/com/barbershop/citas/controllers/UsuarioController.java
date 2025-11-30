package com.barbershop.citas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORTANTE
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Usuario;
import com.barbershop.citas.models.dto.ChangePasswordRequest;
import com.barbershop.citas.repositorys.UsuarioRepository;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Inyectamos esto para poder encriptar la contraseña al actualizar
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear usuario (Básico)
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        // Si creas usuarios por aquí, recuerda encriptar la contraseña también
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }
    
    // Actualizar usuario (CORREGIDO)
    @PutMapping("/{idUsuario}") // Quitamos "/usuarios" para evitar duplicidad
    public ResponseEntity<Usuario> actualizar(@PathVariable int idUsuario, @RequestBody Usuario u) {
        // Usamos usuarioRepository, no 'service'
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(idUsuario);

        if (!usuarioExistenteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuarioExistente = usuarioExistenteOpt.get();

        // Actualizamos datos básicos
        usuarioExistente.setNombres(u.getNombres());
        usuarioExistente.setApellidos(u.getApellidos());
        usuarioExistente.setDni(u.getDni());
        
        // Manejo seguro del correo/email
        // Verificamos si en el JSON viene como 'email' (getter) o 'correo'
        String nuevoCorreo = u.getCorreo(); // Asegúrate que Usuario.java tenga getCorreo() o getEmail()
        if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
             usuarioExistente.setEmail(nuevoCorreo);
        }
        
        usuarioExistente.setCelular(u.getCelular());
        
        // --- PROTECCIÓN DE CONTRASEÑA ---
        // 1. Verificamos que venga una contraseña nueva y no esté vacía
        if (u.getContrasena() != null && !u.getContrasena().isEmpty()) {
            // 2. LA ENCRIPTAMOS antes de guardar
            String passwordEncriptada = passwordEncoder.encode(u.getContrasena());
            usuarioExistente.setContrasena(passwordEncriptada);
        }
        
        // Guardamos usando el repositorio
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }
    
    @PutMapping("/{idUsuario}/cambiar-password")
    public ResponseEntity<?> cambiarContrasena(@PathVariable int idUsuario, @RequestBody ChangePasswordRequest request) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();

        // 1. Verificar que la contraseña ACTUAL coincida con la de la BD
        if (!passwordEncoder.matches(request.getCurrentPassword(), usuario.getContrasena())) {
            return ResponseEntity.badRequest().body("{\"error\": \"La contraseña actual es incorrecta\"}");
        }

        // 2. Encriptar y guardar la NUEVA contraseña
        usuario.setContrasena(passwordEncoder.encode(request.getNewPassword()));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body("{\"mensaje\": \"Contraseña actualizada correctamente\"}");
    }
}