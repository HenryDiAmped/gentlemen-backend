package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.HorariosAtencion;

@Repository
public interface HorariosAtencionRepository extends JpaRepository<HorariosAtencion, Integer>{

}
