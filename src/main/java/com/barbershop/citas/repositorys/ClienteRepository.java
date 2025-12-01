package com.barbershop.citas.repositorys;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbershop.citas.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    Optional<Cliente> findByUsuario_IdUsuario(int idUsuario);
    
    // CAMBIO: Devolvemos Optional para manejar mejor si no existe
    Optional<Cliente> findByDni(String dni);
}