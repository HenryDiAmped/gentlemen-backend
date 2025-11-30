package com.barbershop.citas.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;
    
    @Column(name = "fecha", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    @Column(name = "hora", nullable = false)
    private String hora;
    
    @Column(name = "codigo_confirmacion", nullable = false, unique = true)
    private String codigoConfirmacion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;
    
    public enum Estado {
        ATENDIDO,
        POR_ATENDER,
        CANCELADO
    }
    
    // --- RELACIONES ---
    
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable = true)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="id_barbero", nullable = false)
    private Barbero barbero;
    
    @ManyToOne
    @JoinColumn(name="id_servicio", nullable = false)
    private Servicio servicio;

    // --- NUEVO: Relación con SEDE ---
    @ManyToOne
    @JoinColumn(name="id_sede", nullable = false)
    private Sede sede; 
    
    // Constructor vacío
    public Cita() {}

    // Getters y Setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getCodigoConfirmacion() { return codigoConfirmacion; }
    public void setCodigoConfirmacion(String codigoConfirmacion) { this.codigoConfirmacion = codigoConfirmacion; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Barbero getBarbero() { return barbero; }
    public void setBarbero(Barbero barbero) { this.barbero = barbero; }

    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }

    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }
}