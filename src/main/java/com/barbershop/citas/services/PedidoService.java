package com.barbershop.citas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Importante

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbershop.citas.models.*;
import com.barbershop.citas.models.dto.PedidoDTO;
import com.barbershop.citas.repositorys.*;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private ProductoRepository productoRepo;

    // --- MÉTODOS BÁSICOS (Para arreglar tus errores) ---

    // 1. Listar todos
    public List<Pedido> list() { 
        return pedidoRepo.findAll(); 
    }

    // 2. Buscar por ID (Arregla el error de .listById)
    public Optional<Pedido> listById(int id) {
        return pedidoRepo.findById(id);
    }

    // 3. Guardar/Actualizar entidad directa (Arregla el error de .save)
    public Pedido save(Pedido p) {
        return pedidoRepo.save(p);
    }

    // 4. Eliminar (Arregla el error de .delete)
    public boolean delete(int id) {
        if (pedidoRepo.existsById(id)) {
            pedidoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // --- MÉTODOS AVANZADOS (Para nuestra lógica de negocio) ---

    // Listar por Usuario (Para "Mis Pedidos")
    public List<Pedido> listarPorUsuario(int id) { 
        return pedidoRepo.findByCliente_Usuario_IdUsuario(id); 
    }

    // Guardar usando DTO (Para "Checkout")
    @Transactional 
    public Pedido guardarDesdeDTO(PedidoDTO dto) {
        // 1. GESTIÓN DEL CLIENTE
        Cliente cliente = clienteRepo.findByUsuario_IdUsuario(dto.getIdUsuario()).orElse(null);

        if (cliente == null) {
            Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente.setNombres(usuario.getNombres());
            cliente.setApellidos(usuario.getApellidos());
            cliente.setDni(usuario.getDni());
            cliente.setCelular(usuario.getCelular());
            cliente.setCorreo(usuario.getCorreo());
            cliente = clienteRepo.save(cliente);
        }

        // 2. CREAR PEDIDO
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDireccion(dto.getDireccion());
        pedido.setFechaPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setSubtotal(dto.getTotal());
        pedido.setEstado(Pedido.Estado.PROCESANDO);

        // 3. PROCESAR DETALLES
        List<DetallePedido> detalles = new ArrayList<>();
        for (PedidoDTO.DetalleDto d : dto.getDetalles()) {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            
            Producto prod = productoRepo.findById(d.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detalle.setProducto(prod);
            
            detalles.add(detalle);
        }
        pedido.setDetalles(detalles);

        return pedidoRepo.save(pedido);
    }
}