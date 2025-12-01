package com.barbershop.citas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Barbero;
import com.barbershop.citas.services.BarberoService;

@RestController
@RequestMapping("/api/v1")
public class BarberoController {

	@Autowired
	private BarberoService service;
	
	@GetMapping("/barberos")
	public List<Barbero> listar() {
		return service.list();
	}

	@GetMapping("/barberos/{id}")
	public ResponseEntity<Barbero> listarPorId(@PathVariable int id) {
		Optional<Barbero> barbero = service.listById(id);
		if (barbero.isPresent()) {
			return new ResponseEntity<>(barbero.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/barberos")
	public Barbero guardar(@RequestBody Barbero barbero) {
		return service.save(barbero);
	}

	@PutMapping("/barberos/{id}")
	public ResponseEntity<Barbero> actualizar(@PathVariable int id, @RequestBody Barbero barbero) {
		// 1. Verificar si el barbero existe
		Optional<Barbero> barberoExistente = service.listById(id);
		
		if (barberoExistente.isPresent()) {
			barbero.setIdBarbero(id); 
			Barbero barberoActualizado = service.save(barbero);
			return new ResponseEntity<>(barberoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/barberos/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable int id) {
		if (service.delete(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}