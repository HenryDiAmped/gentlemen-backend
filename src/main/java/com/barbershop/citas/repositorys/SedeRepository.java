package com.barbershop.citas.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbershop.citas.models.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    // No necesitas métodos extra por ahora
}