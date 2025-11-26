package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}