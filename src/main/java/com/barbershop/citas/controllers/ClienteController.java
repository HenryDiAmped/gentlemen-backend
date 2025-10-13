package com.barbershop.citas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Cliente;
import com.barbershop.citas.services.ClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@GetMapping("/clientes")
	public List<Cliente> listar(){
		return service.list();
	}
}
