package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // <--- FALTABA ESTO
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.citas.models.Producto;
import com.barbershop.citas.services.ProductoService;

@RestController
@RequestMapping("/api/v1/products") // Esta es la base de la URL
@CrossOrigin(origins = "http://localhost:4200") // <--- 1. IMPORTANTE: Habilitar Angular
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	// 2. CORRECCIÓN DE RUTAS: Quitamos "/productos" para no duplicar.
	// La URL final será: GET /api/v1/products
	@GetMapping 
	public List<Producto> listar(){
		return service.list();
	}
	
	@PostMapping
	public Producto guardar(@RequestBody Producto p) {
		return service.save(p);
	}
	
	@PutMapping("/{idProducto}")
	public ResponseEntity<Producto> actualizar(@PathVariable int idProducto, @RequestBody Producto p) {
	    Optional<Producto> productoExistenteOpt = service.listById(idProducto);
	    
	    if (!productoExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Producto productoExistente = productoExistenteOpt.get();
	    
	    // --- ACTUALIZACIÓN DE CAMPOS ---
	    productoExistente.setNombre(p.getNombre());
	    productoExistente.setDescripcion(p.getDescripcion());
	    productoExistente.setPrecio(p.getPrecio());
	    productoExistente.setStock(p.getStock());
	    productoExistente.setCategoria(p.getCategoria());
	    productoExistente.setMarca(p.getMarca());
	    productoExistente.setImagenUrl(p.getImagenUrl()); 

	    Producto productoActualizado = service.save(productoExistente);
	    return ResponseEntity.ok(productoActualizado);
	}

	@DeleteMapping("/{idProducto}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idProducto) {
		// Nota: Asegúrate de que tu servicio tenga el método delete que retorne boolean
		// O usa try-catch si tu servicio lanza excepción
		boolean eliminado = service.delete(idProducto);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); 
		}
	}
	
	@GetMapping("/{idProducto}")
	public ResponseEntity<Producto> listarPorId(@PathVariable int idProducto) {
		Optional<Producto> prod = service.listById(idProducto);
	    return prod.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}