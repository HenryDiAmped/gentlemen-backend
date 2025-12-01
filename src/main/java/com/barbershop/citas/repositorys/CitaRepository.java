package com.barbershop.citas.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    // --- CORRECCIÓN AQUÍ ---
    // ANTES (Error): findByUsuario_IdUsuario(int idUsuario);
    // AHORA (Correcto): Buscamos dentro de 'cliente', luego dentro de 'usuario'
    List<Cita> findByCliente_Usuario_IdUsuario(int idUsuario);
    
    // Buscar por código de confirmación (para cancelar)
    Optional<Cita> findByCodigoConfirmacion(String codigoConfirmacion);
}