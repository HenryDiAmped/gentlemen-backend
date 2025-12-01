package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Categoria;
import com.barbershop.citas.services.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService service;
    
    @GetMapping
    public List<Categoria> listar() {
        return service.list();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarPorId(@PathVariable int id) {
        Optional<Categoria> cat = service.listById(id);
        return cat.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Categoria guardar(@RequestBody Categoria c) {
        return service.save(c);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable int id, @RequestBody Categoria c) {
        Optional<Categoria> categoriaExistenteOpt = service.listById(id);
        
        if (!categoriaExistenteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoriaExistente = categoriaExistenteOpt.get();
        
        // Actualizamos los campos
        categoriaExistente.setNombre(c.getNombre());
        categoriaExistente.setDescripcion(c.getDescripcion());

        Categoria categoriaActualizado = service.save(categoriaExistente);
        return ResponseEntity.ok(categoriaActualizado);
    }

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
