package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Barbero;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Integer>{

}
