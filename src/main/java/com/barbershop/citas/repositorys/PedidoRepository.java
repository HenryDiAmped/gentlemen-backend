package com.barbershop.citas.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByCliente_Usuario_IdUsuario(int idUsuario);
}
