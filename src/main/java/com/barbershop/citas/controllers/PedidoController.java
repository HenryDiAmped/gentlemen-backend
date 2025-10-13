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

import com.barbershop.citas.models.Pedido;
import com.barbershop.citas.services.PedidoService;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@GetMapping("/pedidos")
	public List<Pedido> listar(){
		return service.list();
	}
	
	@PostMapping("/pedidos")
	public Pedido guardar(@RequestBody Pedido p) {
		return service.save(p);
	}
	
	@PutMapping("/pedidos/{idPedido}")
	public ResponseEntity<Pedido> actualizar(@PathVariable int idPedido, @RequestBody Pedido p) {
	    Optional<Pedido> pedidoExistenteOpt = service.listById(idPedido);
	    
	    if (!pedidoExistenteOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Pedido pedidoExistente = pedidoExistenteOpt.get();
	    
	    pedidoExistente.setEstado(p.getEstado());
	    pedidoExistente.setTotal(p.getTotal());
	    pedidoExistente.setSubtotal(p.getSubtotal());
	    pedidoExistente.setFechaPedido(p.getFechaPedido());
	    pedidoExistente.setDireccion(p.getDireccion());

	    Pedido pedidoActualizado = service.save(pedidoExistente);
	    return ResponseEntity.ok(pedidoActualizado);
	}

	
	@DeleteMapping("/pedidos/{idPedido}")
	public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable int idPedido) {
		boolean eliminado = service.delete(idPedido);
		Map<String, Boolean> response = new HashMap<>();
		
		if (eliminado) {
			response.put("deleted", true);
			return ResponseEntity.ok(response);
		} else {
			response.put("deleted", false);
			return ResponseEntity.status(404).body(response); // 404 Not Found
		}
	}
	
	@GetMapping("/pedidos/{idPedido}")
	public ResponseEntity<Pedido> listarPorId(@PathVariable int idPedido) {
		Optional<Pedido> pedido = service.listById(idPedido);
	    return pedido.map(ResponseEntity::ok)
	              .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
