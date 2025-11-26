package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbershop.citas.models.Marca;
import com.barbershop.citas.repositorys.MarcaRepository; // Asegúrate de que este paquete sea correcto

@Service
@Transactional // Gestiona las transacciones de base de datos automáticamente
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    // Listar todas las marcas
    public List<Marca> list() {
        return repository.findAll();
    }

    // Buscar por ID (recibe int porque así lo definimos en el Controller)
    public Optional<Marca> listById(int id) {
        // El repositorio espera un Integer, pero Java convierte el int automáticamente
        return repository.findById(id);
    }

    // Guardar o Actualizar
    public Marca save(Marca marca) {
        return repository.save(marca);
    }

    // Eliminar por ID (devuelve true si se borró, false si no existía)
    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}