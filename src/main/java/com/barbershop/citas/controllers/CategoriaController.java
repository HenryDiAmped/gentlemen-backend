package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/categorias")
	public List<Categoria> listar(){
		return service.list();
	}
	
	@PostMapping("/categorias")
	public Categoria guardar(@RequestBody Categoria c) {
		return service.save(c);
	}
	
	@PutMapping("/categorias/{idCategoria}")
	public ResponseEntity<Categoria> actualizar(@PathVariable int idCategoria, @RequestBody Categoria c) {
	    Optional<Categoria> categoriaExistenteOpt = service.listById(idCategoria);
	    
	    if (!categoriaExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Categoria categoriaExistente = categoriaExistenteOpt.get();
	    
	    categoriaExistente.setNombre(c.getNombre());

	    Categoria categoriaActualizado = service.save(categoriaExistente);
	    return ResponseEntity.ok(categoriaActualizado);
	}

	
	@DeleteMapping("/categorias/{idCategoria}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idCategoria) {
		boolean eliminado = service.delete(idCategoria);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/categorias/{idCategoria}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable int idCategoria) {
		Optional<Categoria> cat = service.listById(idCategoria);
	    return cat.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
