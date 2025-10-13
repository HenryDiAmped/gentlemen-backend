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

import com.barbershop.citas.models.HorariosAtencion;
import com.barbershop.citas.services.HorariosAtencionService;

@RestController
@RequestMapping("/api/v1")
public class HorariosAtencionController {
	@Autowired
	private HorariosAtencionService service;
	
	@GetMapping("/horariosAtencion")
	public List<HorariosAtencion> listar(){
		return service.list();
	}
	
	@PostMapping("/horariosAtencion")
	public HorariosAtencion guardar(@RequestBody HorariosAtencion h) {
		return service.save(h);
	}
	
	@PutMapping("/horariosAtencion/{idHorarioAtencion}")
	public ResponseEntity<HorariosAtencion> actualizar(@PathVariable int idHorarioAtencion, @RequestBody HorariosAtencion h) {
	    Optional<HorariosAtencion> horariosAtencionExistenteOpt = service.listById(idHorarioAtencion);
	    
	    if (!horariosAtencionExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    HorariosAtencion horariosAtencionExistente = horariosAtencionExistenteOpt.get();
	    
	    horariosAtencionExistente.setHora(h.getHora());
	    horariosAtencionExistente.setFecha(h.getFecha());
	    horariosAtencionExistente.setEstado(h.getEstado());
	    horariosAtencionExistente.setBarbero(h.getBarbero());

	    HorariosAtencion horariosAtencionActualizado = service.save(horariosAtencionExistente);
	    return ResponseEntity.ok(horariosAtencionActualizado);
	}

	
	@DeleteMapping("/horariosAtencion/{idHorarioAtencion}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idHorarioAtencion) {
		boolean eliminado = service.delete(idHorarioAtencion);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/horariosAtencion/{idHorarioAtencion}")
	public ResponseEntity<HorariosAtencion> listarPorId(@PathVariable int idHorarioAtencion) {
		Optional<HorariosAtencion> hor = service.listById(idHorarioAtencion);
	    return hor.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
