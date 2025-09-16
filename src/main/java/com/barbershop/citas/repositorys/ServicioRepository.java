package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>{

}
