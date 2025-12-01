package com.barbershop.citas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbershop.citas.models.Cliente;
import com.barbershop.citas.repositorys.ClienteRepository;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable int id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- NUEVO: Endpoint para buscar cliente por el ID del Usuario (Login) ---
    // Esto servirá para que Angular cargue los datos del perfil
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Cliente> obtenerPorIdUsuario(@PathVariable int idUsuario) {
        return clienteRepository.findByUsuario_IdUsuario(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Cliente> buscarPorDni(@PathVariable String dni) {
        // Actualizado para usar Optional
        return clienteRepository.findByDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // --- NUEVO: Actualizar Cliente (FALTABA ESTO) ---
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente datosActualizados) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            // Actualizamos solo los datos permitidos
            cliente.setNombres(datosActualizados.getNombres());
            cliente.setApellidos(datosActualizados.getApellidos());
            cliente.setCelular(datosActualizados.getCelular());
            cliente.setCorreo(datosActualizados.getCorreo());
            // El DNI y Usuario usualmente no se cambian aquí por seguridad
            
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}