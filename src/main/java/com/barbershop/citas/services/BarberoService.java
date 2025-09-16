package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Barbero;
import com.barbershop.citas.repositorys.BarberoRepository;

@Service
public class BarberoService {
	@Autowired
	private BarberoRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Barbero save(Barbero b) {
		return repository.save(b);
	}

	// Listar: para recuperar a todos
	public List<Barbero> list() {
		return (List<Barbero>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Barbero> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Barbero> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
