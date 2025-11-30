package com.barbershop.citas.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{
	// Busca todas las citas por id del cliente
	List<Cita> findByCliente_Usuario_IdUsuario(int idUsuario);
	Optional<Cita> findByCodigoConfirmacion(String codigoConfirmacion);
}
