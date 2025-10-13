package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Sede;
import com.barbershop.citas.repositorys.SedeRepository;

@Service
public class SedeService {
	@Autowired
	private SedeRepository repository;
	
	// Guardar: para agregar nuevos y modificar
	public Sede save(Sede s) {
		return repository.save(s);
	}

	// Listar: para recuperar a todos
	public List<Sede> list() {
		return (List<Sede>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Sede> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Sede> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
