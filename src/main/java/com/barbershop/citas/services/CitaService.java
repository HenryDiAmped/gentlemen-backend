package com.barbershop.citas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

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
    @Autowired private ClienteRepository clienteRepository; // O UsuarioRepository
    @Autowired private SedeRepository sedeRepository; 

    // --- MÉTODO CREAR CITA CORREGIDO ---
    public Cita crearCita(CitaDTO dto) {
        
        // 1. Validaciones Básicas de Nulos (Evita NullPointerException)
        if (dto.getCliente() == null || dto.getBarbero() == null || dto.getServicio() == null) {
            throw new RuntimeException("Faltan datos obligatorios (cliente, barbero o servicio).");
        }

        Cita cita = new Cita();
        
        // 2. Conversión de Fecha (String -> LocalDate)
        // El DTO trae "2025-12-10", la Entidad quiere LocalDate
        cita.setFecha(LocalDate.parse(dto.getFecha())); 
        cita.setHora(dto.getHora());
        
        // 3. Generar Código si no viene
        String codigo = dto.getCodigoConfirmacion();
        if (codigo == null || codigo.isEmpty()) {
            codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
        cita.setCodigoConfirmacion(codigo);
        
        cita.setEstado(Cita.Estado.POR_ATENDER);

        // 4. RELACIÓN CON SEDE
        if (dto.getSede() != null) {
            Sede sede = sedeRepository.findById(dto.getSede().idSede)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + dto.getSede().idSede));
            cita.setSede(sede);
        } else {
             throw new RuntimeException("La sede es obligatoria.");
        }

        // 5. RELACIÓN CON BARBERO
        Barbero barbero = barberoRepository.findById(dto.getBarbero().idBarbero)
            .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
        cita.setBarbero(barbero);

        // 6. RELACIÓN CON SERVICIO
        Servicio servicio = servicioRepository.findById(dto.getServicio().idServicio)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        cita.setServicio(servicio);

        // 7. RELACIÓN CON CLIENTE
        // NOTA IMPORTANTE: Ya no creamos el cliente aquí porque el DTO solo trae el ID.
        // Asumimos que el cliente ya se registró previamente.
        Cliente cliente = clienteRepository.findById(dto.getCliente().idUsuario)
                .orElseThrow(() -> new RuntimeException("Cliente/Usuario no encontrado en BD"));
        
        cita.setCliente(cliente);

        return citaRepository.save(cita);
    }

    // Listar mis reservas
    public List<Cita> listarCitasPorUsuario(int idUsuario) {
        // Asegúrate que tu repositorio tenga este método. 
        // Si usas Usuario en vez de Cliente, puede ser: findByCliente_IdUsuario(idUsuario)
        // O si la relación es directa: findByClienteId(idUsuario)
        return citaRepository.findByCliente_Usuario_IdUsuario(idUsuario);
    }

    // Cancelar con regla de 2 horas
    public void cancelarCita(String codigoConfirmacion) {
        Cita cita = citaRepository.findByCodigoConfirmacion(codigoConfirmacion)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        
        if (cita.getEstado() == Cita.Estado.CANCELADO) {
            throw new RuntimeException("La cita ya estaba cancelada.");
        }

        // Convertimos la fecha y hora de la cita a un objeto LocalDateTime completo
        LocalDateTime fechaHoraCita = LocalDateTime.of(
            cita.getFecha(), 
            LocalTime.parse(cita.getHora()) 
        );
        
        LocalDateTime ahora = LocalDateTime.now();
        long horasDiferencia = ChronoUnit.HOURS.between(ahora, fechaHoraCita);

        // Validación: Solo cancelar si faltan más de 2 horas
        // Nota: Si la cita ya pasó (diferencia negativa), tampoco debería dejar cancelar, o sí? 
        // Usualmente no se cancelan citas pasadas.
        if (horasDiferencia < 2) {
            throw new RuntimeException("No se puede cancelar con menos de 2 horas de anticipación o si ya pasó.");
        }
                
        cita.setEstado(Cita.Estado.CANCELADO);
        citaRepository.save(cita);
    }
    
    // ADMIN: Listar todas las citas
    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }
}