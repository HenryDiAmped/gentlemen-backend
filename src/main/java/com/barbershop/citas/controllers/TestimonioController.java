package com.barbershop.citas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.barbershop.citas.models.Testimonio;
import com.barbershop.citas.services.TestimonioService;

@RestController
@RequestMapping("/api/v1/testimonios")
@CrossOrigin(origins = "http://localhost:4200")
public class TestimonioController {

    @Autowired
    private TestimonioService service;

    // Guardar nuevo testimonio (desde el formulario web)
    @PostMapping
    public ResponseEntity<Testimonio> crear(@RequestBody Testimonio t) {
        return ResponseEntity.ok(service.guardar(t));
    }

    // Listar solo los aprobados (para el Home)
    @GetMapping("/aprobados")
    public List<Testimonio> listarAprobados() {
        return service.listarAprobados();
    }
    
    // ADMIN: Obtener todos (Corregido: Usa Testimonio, no Testimonial)
    @GetMapping
    public List<Testimonio> listarTodos() {
        return service.listarTodos();
    }

    // ADMIN: Aprobar/Rechazar
    @PutMapping("/{id}/estado")
    public ResponseEntity<Testimonio> cambiarEstado(@PathVariable int id, @RequestParam String estado) {
        try {
            Testimonio.Estado nuevoEstado = Testimonio.Estado.valueOf(estado.toUpperCase());
            return ResponseEntity.ok(service.cambiarEstado(id, nuevoEstado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ADMIN: Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}