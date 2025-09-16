package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer>{
	
}
