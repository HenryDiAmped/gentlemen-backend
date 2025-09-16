package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Carrito;
import com.barbershop.citas.repositorys.CarritoRepository;

@Service
public class CarritoService {
	@Autowired
	private CarritoRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Carrito save(Carrito c) {
		return repository.save(c);
	}

	// Listar: para recuperar a todos
	public List<Carrito> list() {
		return (List<Carrito>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Carrito> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Carrito> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
