package com.barbershop.citas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbershop.citas.models.Pedido;
import com.barbershop.citas.models.dto.PedidoDTO; // Importante: Importar el DTO
import com.barbershop.citas.services.PedidoService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("/pedidos")
    public List<Pedido> listar(){
        return service.list();
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> guardar(@RequestBody PedidoDTO pedidoDto) {
        Pedido nuevoPedido = service.guardarDesdeDTO(pedidoDto);
        return ResponseEntity.ok(nuevoPedido);
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
            return ResponseEntity.status(404).body(response);
        }
    }
    
    @GetMapping("/pedidos/{idPedido}")
    public ResponseEntity<Pedido> listarPorId(@PathVariable int idPedido) {
        Optional<Pedido> pedido = service.listById(idPedido);
        return pedido.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pedidos/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> listarPorUsuario(@PathVariable int idUsuario) {
        List<Pedido> pedidos = service.listarPorUsuario(idUsuario);
        return ResponseEntity.ok(pedidos);
    }
}