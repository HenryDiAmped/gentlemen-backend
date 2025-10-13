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

import com.barbershop.citas.models.Carrito;
import com.barbershop.citas.services.CarritoService;

@RestController
@RequestMapping("/api/v1")
public class CarritoController {
	@Autowired
	private CarritoService service;
	
	@GetMapping("/carritos")
	public List<Carrito> listar(){
		return service.list();
	}
	
	@PostMapping("/carritos")
	public Carrito guardar(@RequestBody Carrito c) {
		return service.save(c);
	}
	
	@PutMapping("/carritos/{idCarrito}")
	public ResponseEntity<Carrito> actualizar(@PathVariable int idCarrito, @RequestBody Carrito c) {
	    Optional<Carrito> carritoExistenteOpt = service.listById(idCarrito);
	    
	    if (!carritoExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Carrito carritoExistente = carritoExistenteOpt.get();
	    
	    carritoExistente.setFechaCreacion(c.getFechaCreacion());
	    carritoExistente.setEstado(c.getEstado());
	    carritoExistente.setCantidad(c.getCantidad());
	    carritoExistente.setProducto(c.getProducto());
	    carritoExistente.setPedido(c.getPedido());
	    carritoExistente.setCliente(c.getCliente());

	    Carrito carritoActualizado = service.save(carritoExistente);
	    return ResponseEntity.ok(carritoActualizado);
	}

	
	@DeleteMapping("/carritos/{idCarrito}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idCarrito) {
		boolean eliminado = service.delete(idCarrito);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/carritos/{idCarrito}")
	public ResponseEntity<Carrito> listarPorId(@PathVariable int idCarrito) {
		Optional<Carrito> carrito = service.listById(idCarrito);
	    return carrito.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
