package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Cliente;
import com.barbershop.citas.repositorys.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	// Guardar: para agregar nuevos y modificar
	public Cliente save(Cliente c) {
		return repository.save(c);
	}

	// Listar: para recuperar a todos
	public List<Cliente> list() {
		return (List<Cliente>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<Cliente> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Cliente> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
