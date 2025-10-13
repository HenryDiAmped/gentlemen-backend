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

import com.barbershop.citas.models.Comentario;
import com.barbershop.citas.services.ComentarioService;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {
	@Autowired
	private ComentarioService service;
	
	@GetMapping("/comentarios")
	public List<Comentario> listar(){
		return service.list();
	}
	
	@PostMapping("/comentarios")
	public Comentario guardar(@RequestBody Comentario c) {
		return service.save(c);
	}
	
	@PutMapping("/comentarios/{idComentario}")
	public ResponseEntity<Comentario> actualizar(@PathVariable int idComentario, @RequestBody Comentario c) {
	    Optional<Comentario> comentarioExistenteOpt = service.listById(idComentario);
	    
	    if (!comentarioExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Comentario comentarioExistente = comentarioExistenteOpt.get();
	    
	    comentarioExistente.setContenido(c.getContenido());

	    Comentario comentarioActualizado = service.save(comentarioExistente);
	    return ResponseEntity.ok(comentarioActualizado);
	}

	
	@DeleteMapping("/comentarios/{idComentario}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idComentario) {
		boolean eliminado = service.delete(idComentario);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/comentarios/{idComentario}")
	public ResponseEntity<Comentario> listarPorId(@PathVariable int idComentario) {
		Optional<Comentario> com = service.listById(idComentario);
	    return com.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
