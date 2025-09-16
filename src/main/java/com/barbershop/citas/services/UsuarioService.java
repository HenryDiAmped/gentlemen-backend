package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Usuario;
import com.barbershop.citas.repositorys.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Usuario save(Usuario u) {
		return repository.save(u);
	}

	// Listar: para recuperar a todos
	public List<Usuario> list() {
		return (List<Usuario>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Usuario> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Usuario> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
