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

import com.barbershop.citas.models.Servicio;
import com.barbershop.citas.services.ServicioService;

@RestController
@RequestMapping("/api/v1")
public class ServicioController {
	@Autowired
	private ServicioService service;
	
	@GetMapping("/servicios")
	public List<Servicio> listar(){
		return service.list();
	}
	
	@PostMapping("/servicios")
	public Servicio guardar(@RequestBody Servicio s) {
		return service.save(s);
	}
	
	@PutMapping("/servicios/{idServicio}")
	public ResponseEntity<Servicio> actualizar(@PathVariable int idServicio, @RequestBody Servicio s) {
	    Optional<Servicio> serviciosExistenteOpt = service.listById(idServicio);
	    
	    if (!serviciosExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Servicio serviciosExistente = serviciosExistenteOpt.get();
	    
	    serviciosExistente.setNombre(s.getNombre());
	    serviciosExistente.setDetalle(s.getDetalle());
	    serviciosExistente.setTarifa(s.getTarifa());
	    serviciosExistente.setTipoServicio(s.getTipoServicio());

	    Servicio serviciosActualizado = service.save(serviciosExistente);
	    return ResponseEntity.ok(serviciosActualizado);
	}
	
	@DeleteMapping("/servicios/{idServicio}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idServicio) {
		boolean eliminado = service.delete(idServicio);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/servicios/{idServicio}")
	public ResponseEntity<Servicio> listarPorId(@PathVariable int idServicio) {
		Optional<Servicio> serv = service.listById(idServicio);
	    return serv.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
