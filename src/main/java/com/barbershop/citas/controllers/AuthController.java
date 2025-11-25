package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.Map;

// Importaciones de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesario para seguridad
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importaciones de tus modelos y repositorios
import com.barbershop.citas.models.RegisterRequest;
import com.barbershop.citas.models.Usuario;
import com.barbershop.citas.models.LoginRequest;
import com.barbershop.citas.repositorys.UsuarioRepository;
// Asumo que crearás esta clase en un paquete 'security' o 'utils'
import com.barbershop.citas.security.JwtUtil; 

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Inyectamos el encoder para no comparar texto plano
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inyectamos la utilidad para generar el Token
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Buscamos el usuario por DNI (o username)
        return usuarioRepository.findByDni(request.getUsername())
            .map(usuario -> {
                // 1. Verificación SEGURA de contraseña (Hash vs Texto plano)
                if (passwordEncoder.matches(request.getPassword(), usuario.getContrasena())) {
                    
                    // 2. Generar el Token JWT
                    // (Le pasamos el usuario o el DNI, según cómo configures tu JwtUtil)
                    String token = jwtUtil.generateToken(usuario.getDni());

                    // 3. Preparar el objeto del Usuario para el frontend
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("id", usuario.getIdUsuario());
                    userData.put("firstName", usuario.getNombres());
                    userData.put("lastName", usuario.getApellidos());
                    userData.put("email", usuario.getCorreo()); // Si tienes este campo
                    userData.put("role", usuario.getTipoUsuario());

                    // 4. Preparar la respuesta completa (Token + Usuario)
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token); // ¡Importante para tu AuthService!
                    response.put("role", usuario.getTipoUsuario());
                    response.put("user", userData);
                    
                    return ResponseEntity.ok(response);
                } else {
                    // Contraseña incorrecta
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(crearMensajeError("Credenciales incorrectas"));
                }
            })
            .orElseGet(() -> 
                // Usuario no encontrado
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(crearMensajeError("Credenciales incorrectas"))
            );
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // 1. Validar si el usuario ya existe (por DNI o Email)
        if (usuarioRepository.findByDni(request.getDni()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El DNI ya está registrado.");
        }
        
        // 2. Crear la nueva entidad Usuario
        Usuario nuevoUsuario = new Usuario();
        
        // 3. Mapear los datos (Del Inglés del Frontend al Español del Backend)
        nuevoUsuario.setNombres(request.getFirstName());
        nuevoUsuario.setApellidos(request.getLastName());
        nuevoUsuario.setDni(request.getDni());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setCelular(request.getPhone());
        
        // ¡IMPORTANTE! Encriptar la contraseña antes de guardarla
        nuevoUsuario.setContrasena(passwordEncoder.encode(request.getPassword()));
        
        // Asignar rol por defecto (CLIENTE)
        nuevoUsuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);

        // 4. Guardar en Base de Datos
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
    // Método auxiliar para devolver JSON en errores en lugar de texto plano (opcional pero recomendado)
    private Map<String, String> crearMensajeError(String mensaje) {
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        return error;
    }
}