package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Categoria;
import com.barbershop.citas.repositorys.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Categoria save(Categoria c) {
		return repository.save(c);
	}

	// Listar: para recuperar a todos
	public List<Categoria> list() {
		return (List<Categoria>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Categoria> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Categoria> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
