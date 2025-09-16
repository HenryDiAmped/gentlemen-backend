package com.barbershop.citas.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_general")
@Data
public class AuditoriaGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entidad;      // Ej: "Producto", "Categoria"
    private Long idRegistro;     // ID del registro afectado
    private String accion;       // INSERT, UPDATE, DELETE
    @Lob
    private String datosAnteriores; // JSON serializado
    @Lob
    private String datosNuevos;     // JSON serializado

    private Long usuarioId;      // usuario que hizo la acción
    private LocalDateTime fecha = LocalDateTime.now();
}

