package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Producto;
import com.barbershop.citas.repositorys.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;

		// Guardar: para agregar nuevos y modificar
	public Producto save(Producto p) {
		return repository.save(p);
	}

	// Listar: para recuperar a todos
	public List<Producto> list() {
		return (List<Producto>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Producto> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Producto> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
