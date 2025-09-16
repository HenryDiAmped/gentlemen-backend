package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{

}
