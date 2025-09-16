package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Servicio;
import com.barbershop.citas.repositorys.ServicioRepository;

@Service
public class ServicioService {
	@Autowired
	private ServicioRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Servicio save(Servicio s) {
		return repository.save(s);
	}

	// Listar: para recuperar a todos
	public List<Servicio> list() {
		return (List<Servicio>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Servicio> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Servicio> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
