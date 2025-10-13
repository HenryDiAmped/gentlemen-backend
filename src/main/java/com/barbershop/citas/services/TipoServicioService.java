package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.TipoServicio;
import com.barbershop.citas.repositorys.TipoServicioRepository;

@Service
public class TipoServicioService {
	@Autowired
	private TipoServicioRepository repository;
	
	//Guardar: para agregar nuevos y modificar
	public TipoServicio save(TipoServicio t) {
		return repository.save(t);
	}
	
	//Listar: para recuperar a todos
	public List<TipoServicio> list() {
		return (List<TipoServicio>) repository.findAll();
	}
	
	//Listar por id: recuperar solo uno por id
	public Optional<TipoServicio> listById(int id) {
		return repository.findById(id);
	}
	
	//Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<TipoServicio> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
