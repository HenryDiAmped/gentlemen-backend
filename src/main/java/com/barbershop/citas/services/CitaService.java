package com.barbershop.citas.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.*;
import com.barbershop.citas.models.dto.CitaDTO;
import com.barbershop.citas.repositorys.*; 

@Service
public class CitaService {

    @Autowired private CitaRepository citaRepository;
    @Autowired private BarberoRepository barberoRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    
    // --- NUEVO: Inyectamos SedeRepository ---
    @Autowired private SedeRepository sedeRepository; 

    public Cita crearCita(CitaDTO dto) {
        // 1. Validación de seguridad
        if (dto.getUser().id == null) {
            throw new RuntimeException("Error: Usuario no identificado. Debe iniciar sesión.");
        }

        Cita cita = new Cita();
        
        cita.setFecha(dto.getDate());
        cita.setHora(dto.getTime());
        cita.setCodigoConfirmacion(dto.getConfirmationNumber());
        cita.setEstado(Cita.Estado.POR_ATENDER);

        // 2. RELACIÓN CON SEDE (Usando SedeRepository)
        if (dto.getLocation() == null || dto.getLocation().id == 0) {
            throw new RuntimeException("Error: La sede es obligatoria para agendar la cita.");
        }
        
        Sede sede = sedeRepository.findById(dto.getLocation().id)
        	    .orElseThrow(() -> new RuntimeException("Sede no encontrada en la base de datos"));
        	cita.setSede(sede);

        // 3. Relaciones Básicas
        Barbero barbero = barberoRepository.findById(dto.getBarber().id)
            .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
        cita.setBarbero(barbero);

        Servicio servicio = servicioRepository.findById(dto.getService().id)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        cita.setServicio(servicio);

        // 4. Gestión del Cliente (Vinculado al Usuario)
        Cliente cliente = clienteRepository.findByUsuario_IdUsuario(dto.getUser().id)
                .orElse(null);

        if (cliente == null) {
            // Si es nuevo cliente, lo creamos
            Usuario usuario = usuarioRepository.findById(dto.getUser().id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en BD"));

            cliente = new Cliente();
            cliente.setUsuario(usuario);
            cliente.setNombres(dto.getUser().firstName);
            cliente.setApellidos(dto.getUser().lastName);
            cliente.setDni(dto.getUser().dni);
            cliente.setCelular(dto.getUser().phone);
            cliente.setCorreo(dto.getUser().email);
            
            cliente = clienteRepository.save(cliente);
        } else {
            // Actualizamos teléfono por si cambió
            cliente.setCelular(dto.getUser().phone);
            clienteRepository.save(cliente);
        }
        
        cita.setCliente(cliente);

        return citaRepository.save(cita);
    }

    // Listar mis reservas (Cliente)
    public List<Cita> listarCitasPorUsuario(int idUsuario) {
        return citaRepository.findByCliente_Usuario_IdUsuario(idUsuario);
    }

    // Cancelar con regla de 2 horas
    public void cancelarCita(String codigoConfirmacion) {
        Cita cita = citaRepository.findByCodigoConfirmacion(codigoConfirmacion)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        
        if (cita.getEstado() == Cita.Estado.CANCELADO) {
            throw new RuntimeException("La cita ya estaba cancelada.");
        }

        LocalDateTime fechaHoraCita = LocalDateTime.of(
            cita.getFecha(), 
            LocalTime.parse(cita.getHora()) 
        );
        
        LocalDateTime ahora = LocalDateTime.now();
        long horasDiferencia = ChronoUnit.HOURS.between(ahora, fechaHoraCita);

        if (horasDiferencia < 2) {
            throw new RuntimeException("No se puede cancelar con menos de 2 horas de anticipación.");
        }
                
        cita.setEstado(Cita.Estado.CANCELADO);
        citaRepository.save(cita);
    }
    
    // ADMIN: Listar todas las citas
    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }
}