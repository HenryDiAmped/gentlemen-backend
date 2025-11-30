package com.barbershop.citas.repositorys;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbershop.citas.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    // Buscar si este Usuario ya tiene un perfil de Cliente creado
    Optional<Cliente> findByUsuario_IdUsuario(int idUsuario);
    Cliente findByDni(String dni);
}