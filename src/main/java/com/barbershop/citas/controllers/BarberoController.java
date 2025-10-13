package com.barbershop.citas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Barbero> listar(){
		return service.list();
	}
}
