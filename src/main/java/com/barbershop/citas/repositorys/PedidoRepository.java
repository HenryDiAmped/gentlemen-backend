package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
