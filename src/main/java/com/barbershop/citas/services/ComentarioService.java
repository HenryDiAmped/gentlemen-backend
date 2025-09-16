package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Comentario;
import com.barbershop.citas.repositorys.ComentarioRepository;

@Service
public class ComentarioService {
	@Autowired
	private ComentarioRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Comentario save(Comentario c) {
		return repository.save(c);
	}

	// Listar: para recuperar a todos
	public List<Comentario> list() {
		return (List<Comentario>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Comentario> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Comentario> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
