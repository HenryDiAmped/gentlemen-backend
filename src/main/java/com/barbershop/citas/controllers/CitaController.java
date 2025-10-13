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

import com.barbershop.citas.models.Cita;
import com.barbershop.citas.services.CitaService;

@RestController
@RequestMapping("/api/v1")
public class CitaController {
	@Autowired
	private CitaService service;
	
	@GetMapping("/citas")
	public List<Cita> listar(){
		return service.list();
	}
	
	@PostMapping("/citas")
	public Cita guardar(@RequestBody Cita c) {
		return service.save(c);
	}
	
	@PutMapping("/citas/{idCita}")
	public ResponseEntity<Cita> actualizar(@PathVariable int idCita, @RequestBody Cita c) {
	    Optional<Cita> citaExistenteOpt = service.listById(idCita);
	    
	    if (!citaExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Cita citaExistente = citaExistenteOpt.get();
	    
	    citaExistente.setEstado(c.getEstado());
	    citaExistente.setCliente(c.getCliente());
	    citaExistente.setHorariosAtencion(c.getHorariosAtencion());
	    citaExistente.setComentario(c.getComentario());
	    citaExistente.setServicio(c.getServicio());

	    Cita citaActualizado = service.save(citaExistente);
	    return ResponseEntity.ok(citaActualizado);
	}

	
	@DeleteMapping("/citas/{idCita}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idCita) {
		boolean eliminado = service.delete(idCita);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/citas/{idCita}")
	public ResponseEntity<Cita> listarPorId(@PathVariable int idCita) {
		Optional<Cita> cita = service.listById(idCita);
	    return cita.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/citas/cliente/{idCliente}")
	public List<Cita> listarPorCliente(@PathVariable int idCliente) {
	    return service.listByIdCliente(idCliente);
	}
}
