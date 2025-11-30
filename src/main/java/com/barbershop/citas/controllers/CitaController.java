package com.barbershop.citas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Cita;
import com.barbershop.citas.models.dto.CitaDTO;
import com.barbershop.citas.services.CitaService;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200") // Permite conexión desde Angular
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> agendarCita(@RequestBody CitaDTO citaDto) {
        // Llamamos al servicio que contiene toda la lógica de validación y guardado
        Cita nuevaCita = citaService.crearCita(citaDto);
        return ResponseEntity.ok(nuevaCita);
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Cita>> listarPorUsuario(@PathVariable int idUsuario) {
        List<Cita> citas = citaService.listarCitasPorUsuario(idUsuario);
        return ResponseEntity.ok(citas);
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
    @GetMapping // Esto mapea a GET /api/v1/citas
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }
}