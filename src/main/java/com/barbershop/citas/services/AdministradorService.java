package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.Administrador;
import com.barbershop.citas.repositorys.AdministradorRepository;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository repository;
	
	//Guardar: para agregar nuevos y modificar
	public Administrador save(Administrador a) {
		return repository.save(a);
	}
	
	//Listar: para recuperar a todos
	public List<Administrador> list() {
		return (List<Administrador>) repository.findAll();
	}
	
	//Listar por id: recuperar solo uno por id
	public Optional<Administrador> listById(int id) {
		return repository.findById(id);
	}
	
	//Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<Administrador> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
