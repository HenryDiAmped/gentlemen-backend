package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
