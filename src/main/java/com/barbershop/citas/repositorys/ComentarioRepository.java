package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbershop.citas.models.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

}
