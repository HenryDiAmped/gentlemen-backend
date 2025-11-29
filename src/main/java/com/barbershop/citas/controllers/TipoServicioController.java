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

import com.barbershop.citas.models.TipoServicio;
import com.barbershop.citas.services.TipoServicioService;

@RestController
@RequestMapping("/api/v1")
public class TipoServicioController {
	@Autowired
	private TipoServicioService service;
	
	@GetMapping("/tipoServicios")
	public List<TipoServicio> listar(){
		return service.list();
	}
	
	@PostMapping("/tipoServicios")
	public TipoServicio guardar(@RequestBody TipoServicio t) {
		return service.save(t);
	}
	
	@PutMapping("/tipoServicios/{idTipoServicio}")
	public ResponseEntity<TipoServicio> actualizar(@PathVariable int idTipoServicio, @RequestBody TipoServicio t) {
	    Optional<TipoServicio> tipoServicioExistenteOpt = service.listById(idTipoServicio);
	    
	    if (!tipoServicioExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    TipoServicio tipoServicioExistente = tipoServicioExistenteOpt.get();
	    
	    tipoServicioExistente.setNombre(t.getNombre());
	    tipoServicioExistente.setServicios(t.getServicios());

	    TipoServicio tipoServicioActualizado = service.save(tipoServicioExistente);
	    return ResponseEntity.ok(tipoServicioActualizado);
	}

	
	@DeleteMapping("/tipoServicios/{idTipoServicio}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idTipoServicio) {
		boolean eliminado = service.delete(idTipoServicio);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/tipoServicios/{idTipoServicio}")
	public ResponseEntity<TipoServicio> listarPorId(@PathVariable int idTipoServicio) {
		Optional<TipoServicio> tipoServ = service.listById(idTipoServicio);
	    return tipoServ.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
