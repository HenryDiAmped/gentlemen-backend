package com.barbershop.citas.models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name="Testimonios")
public class Testimonio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testimonio")
    private int idTestimonio;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @Column(name = "comentario", nullable = false, length = 500)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    public enum Estado {
        PENDIENTE, APROBADO, RECHAZADO
    }

    // Constructor vacío
    public Testimonio() {}

    // Getters y Setters
    public int getIdTestimonio() { return idTestimonio; }
    public void setIdTestimonio(int idTestimonio) { this.idTestimonio = idTestimonio; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}