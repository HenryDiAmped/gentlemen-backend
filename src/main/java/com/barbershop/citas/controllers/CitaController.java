package com.barbershop.citas.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbershop.citas.models.*;
import com.barbershop.citas.models.dto.CitaDTO;
import com.barbershop.citas.repositorys.*;
import com.barbershop.citas.services.CitaService;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaController {

    @Autowired private CitaService citaService;
    @Autowired private CitaRepository citaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private BarberoRepository barberoRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private SedeRepository sedeRepository;
    
    // --- NUEVO: Necesitamos esto para leer los datos del Usuario y crear el Cliente ---
    @Autowired private UsuarioRepository usuarioRepository; 

    @PostMapping
    public ResponseEntity<?> agendarCita(@RequestBody CitaDTO dto) {
        try {
            if (dto.getCliente() == null || dto.getBarbero() == null || dto.getServicio() == null) {
                return ResponseEntity.badRequest().body("Faltan datos obligatorios.");
            }

            Cita nuevaCita = new Cita();
            nuevaCita.setFecha(java.time.LocalDate.parse(dto.getFecha()));
            nuevaCita.setHora(dto.getHora());

            String codigo = dto.getCodigoConfirmacion();
            if (codigo == null || codigo.isEmpty()) {
                codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            }
            nuevaCita.setCodigoConfirmacion(codigo);
            nuevaCita.setEstado(Cita.Estado.POR_ATENDER);

            // --- LÓGICA CORREGIDA PARA EL CLIENTE ---
            int idUsuario = dto.getCliente().idUsuario;

            // 1. Buscamos si ya existe un cliente vinculado a este usuario
            // (Nota: Asegúrate de tener findByUsuario_IdUsuario en tu ClienteRepository)
            Cliente cliente = clienteRepository.findByUsuario_IdUsuario(idUsuario).orElse(null);

            // 2. Si no existe (es tu caso con el ID 18), lo creamos ahora mismo
            if (cliente == null) {
                Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

                cliente = new Cliente();
                cliente.setUsuario(usuario);
                // Copiamos datos del usuario al cliente
                cliente.setNombres(usuario.getNombres());
                cliente.setApellidos(usuario.getApellidos());
                cliente.setDni(usuario.getDni());
                cliente.setCelular(usuario.getCelular());
                cliente.setCorreo(usuario.getCorreo());
                
                // Guardamos el nuevo cliente
                cliente = clienteRepository.save(cliente);
            }
            
            nuevaCita.setCliente(cliente);
            // ----------------------------------------

            // BARBERO
            Barbero barbero = barberoRepository.findById(dto.getBarbero().idBarbero)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
            nuevaCita.setBarbero(barbero);

            // SERVICIO
            Servicio servicio = servicioRepository.findById(dto.getServicio().idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
            nuevaCita.setServicio(servicio);

            // SEDE
            if (dto.getSede() != null) {
                Sede sede = sedeRepository.findById(dto.getSede().idSede)
                    .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
                nuevaCita.setSede(sede);
            }

            Cita citaGuardada = citaRepository.save(nuevaCita);
            return ResponseEntity.status(HttpStatus.CREATED).body(citaGuardada);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error guardando cita: " + e.getMessage());
        }
    }
    
    // ... tus otros métodos (listar, cancelar, etc) ...
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Cita>> listarPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(citaService.listarCitasPorUsuario(idUsuario));
    }
    
    @PutMapping("/cancelar/{codigo}")
    public ResponseEntity<?> cancelarCita(@PathVariable String codigo) {
        try {
            citaService.cancelarCita(codigo);
            return ResponseEntity.ok().body("{\"mensaje\": \"Cita cancelada correctamente\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }
}