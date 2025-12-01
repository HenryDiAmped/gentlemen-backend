package com.barbershop.citas.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.barbershop.citas.models.Usuario;
import com.barbershop.citas.models.dto.ChangePasswordRequest;
import com.barbershop.citas.repositorys.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        // Aunque el frontend envía el dato bajo la llave "email" (por el servicio legacy),
        // nosotros tomamos ese valor y lo tratamos como DNI.
        String dniIngresado = credentials.get("email"); 
        String password = credentials.get("password");

        // 1. Buscamos EXCLUSIVAMENTE por DNI
        Optional<Usuario> usuarioOpt = usuarioRepository.findByDni(dniIngresado);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // 2. Verificamos contraseña
            if (passwordEncoder.matches(password, usuario.getContrasena())) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Contraseña incorrecta"));
            }
        }
        
        return ResponseEntity.badRequest().body(Map.of("error", "DNI no registrado"));
    }
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }
    
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> actualizar(@PathVariable int idUsuario, @RequestBody Usuario u) {
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(idUsuario);

        if (!usuarioExistenteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuarioExistente = usuarioExistenteOpt.get();

        // Actualizamos datos usando los nombres correctos del modelo
        usuarioExistente.setNombres(u.getNombres());
        usuarioExistente.setApellidos(u.getApellidos());
        usuarioExistente.setDni(u.getDni());
        
        String nuevoCorreo = u.getCorreo(); 
        if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
             usuarioExistente.setEmail(nuevoCorreo);
        }
        
        usuarioExistente.setCelular(u.getCelular());
        
        if (u.getContrasena() != null && !u.getContrasena().isEmpty()) {
            String passwordEncriptada = passwordEncoder.encode(u.getContrasena());
            usuarioExistente.setContrasena(passwordEncriptada);
        }
        
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

        if (!passwordEncoder.matches(request.getCurrentPassword(), usuario.getContrasena())) {
            return ResponseEntity.badRequest().body(Map.of("error", "La contraseña actual es incorrecta"));
        }

        usuario.setContrasena(passwordEncoder.encode(request.getNewPassword()));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }
}