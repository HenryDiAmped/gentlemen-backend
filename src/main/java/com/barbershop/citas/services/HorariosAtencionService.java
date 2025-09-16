package com.barbershop.citas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.citas.models.HorariosAtencion;
import com.barbershop.citas.repositorys.HorariosAtencionRepository;

@Service
public class HorariosAtencionService {
	@Autowired
	private HorariosAtencionRepository repository;

	// Guardar: para agregar nuevos y modificar
	public HorariosAtencion save(HorariosAtencion h) {
		return repository.save(h);
	}

	// Listar: para recuperar a todos
	public List<HorariosAtencion> list() {
		return (List<HorariosAtencion>) repository.findAll();
	}

	// Listar por id: recuperar solo uno por id
	public Optional<HorariosAtencion> listById(int id) {
		return repository.findById(id);
	}

	// Eliminar: para eliminar un usuario por su id
	public boolean delete(int id) {
		Optional<HorariosAtencion> Opt = listById(id);
		if (Opt.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
