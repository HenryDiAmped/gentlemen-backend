package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbershop.citas.models.Marca;
import com.barbershop.citas.services.MarcaService;

@RestController
@RequestMapping("/api/v1/marcas")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {

    @Autowired
    private MarcaService service;

    // Listar todas las marcas
    @GetMapping
    public List<Marca> listar() {
        return service.list();
    }

    // Listar por ID (usando int)
    @GetMapping("/{id}")
    public ResponseEntity<Marca> listarPorId(@PathVariable int id) {
        Optional<Marca> marca = service.listById(id);
        return marca.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva marca
    @PostMapping
    public Marca guardar(@RequestBody Marca marca) {
        return service.save(marca);
    }

    // Actualizar marca existente
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizar(@PathVariable int id, @RequestBody Marca marca) {
        Optional<Marca> marcaExistenteOpt = service.listById(id);

        if (!marcaExistenteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Marca marcaExistente = marcaExistenteOpt.get();
        
        // Actualizamos los datos
        marcaExistente.setNombre(marca.getNombre());
        marcaExistente.setDescripcion(marca.getDescripcion());

        Marca marcaActualizada = service.save(marcaExistente);
        return ResponseEntity.ok(marcaActualizada);
    }

    // Eliminar marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable int id) {
        boolean eliminado = service.delete(id);
        Map<String, Boolean> response = new HashMap<>();

        if (eliminado) {
            response.put("deleted", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("deleted", false);
            return ResponseEntity.status(404).body(response);
        }
    }
}