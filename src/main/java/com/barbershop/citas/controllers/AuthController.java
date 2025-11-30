package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.RegisterRequest;
import com.barbershop.citas.models.Usuario;
import com.barbershop.citas.models.LoginRequest;
import com.barbershop.citas.repositorys.UsuarioRepository;
import com.barbershop.citas.security.JwtUtil; 

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Buscamos por DNI (que viene en el campo username del request)
        return usuarioRepository.findByDni(request.getUsername())
            .map(usuario -> {
                // Comparamos la contraseña encriptada
                if (passwordEncoder.matches(request.getPassword(), usuario.getContrasena())) {
                    
                    String token = jwtUtil.generateToken(usuario.getDni());

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("id", usuario.getIdUsuario());
                    userData.put("firstName", usuario.getNombres());
                    userData.put("lastName", usuario.getApellidos());
                    userData.put("email", usuario.getCorreo());
                    userData.put("dni", usuario.getDni());
                    userData.put("phone", usuario.getCelular());
                    userData.put("role", usuario.getTipoUsuario());

                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("role", usuario.getTipoUsuario());
                    response.put("user", userData);
                    
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(crearMensajeError("Credenciales incorrectas"));
                }
            })
            .orElseGet(() -> 
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(crearMensajeError("Credenciales incorrectas"))
            );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // 1. Validar si ya existe
        if (usuarioRepository.findByDni(request.getDni()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El DNI ya está registrado.");
        }
        
        // 2. Crear Usuario
        Usuario nuevoUsuario = new Usuario();
        
        // 3. Mapear datos (Inglés -> Español)
        nuevoUsuario.setNombres(request.getFirstName());
        nuevoUsuario.setApellidos(request.getLastName());
        nuevoUsuario.setDni(request.getDni());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setCelular(request.getPhone());
        
        // 4. Encriptar contraseña (AQUÍ ESTABA EL ERROR 500 ANTES)
        // Asegúrate que RegisterRequest tenga el getter getPassword()
        if (request.getPassword() != null) {
            nuevoUsuario.setContrasena(passwordEncoder.encode(request.getPassword()));
        } else {
             return ResponseEntity.badRequest().body("Error: La contraseña es obligatoria.");
        }
        
        // 5. Asignar rol
        nuevoUsuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);

        // 6. Guardar SOLO en Usuarios (ya que no usas tabla Clientes)
        usuarioRepository.save(nuevoUsuario);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario registrado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    private Map<String, String> crearMensajeError(String mensaje) {
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        return error;
    }
}