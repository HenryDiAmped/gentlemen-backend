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

import com.barbershop.citas.models.Producto;
import com.barbershop.citas.services.ProductoService;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@GetMapping("/productos")
	public List<Producto> listar(){
		return service.list();
	}
	
	@PostMapping("/productos")
	public Producto guardar(@RequestBody Producto p) {
		return service.save(p);
	}
	
	@PutMapping("/productos/{idProducto}")
	public ResponseEntity<Producto> actualizar(@PathVariable int idProducto, @RequestBody Producto p) {
	    Optional<Producto> productoExistenteOpt = service.listById(idProducto);
	    
	    if (!productoExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Producto productoExistente = productoExistenteOpt.get();
	    
	    productoExistente.setNombre(p.getNombre());
	    productoExistente.setDescripcion(p.getDescripcion());
	    productoExistente.setPrecio(p.getPrecio());
	    productoExistente.setStock(p.getStock());
	    productoExistente.setCategoria(p.getCategoria());

	    Producto productoActualizado = service.save(productoExistente);
	    return ResponseEntity.ok(productoActualizado);
	}

	
	@DeleteMapping("/productos/{idProducto}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idProducto) {
		boolean eliminado = service.delete(idProducto);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/productos/{idProducto}")
	public ResponseEntity<Producto> listarPorId(@PathVariable int idProducto) {
		Optional<Producto> sede = service.listById(idProducto);
	    return sede.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
