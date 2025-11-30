package com.barbershop.citas.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.citas.models.Testimonio;
import com.barbershop.citas.repositorys.TestimonioRepository;

@Service
public class TestimonioService {
    @Autowired
    private TestimonioRepository repo;

    public Testimonio guardar(Testimonio t) {
        // Por defecto, siempre se crea como PENDIENTE y con fecha de hoy
        if (t.getEstado() == null) {
            t.setEstado(Testimonio.Estado.PENDIENTE);
        }
        if (t.getFecha() == null) {
            t.setFecha(LocalDate.now());
        }
        return repo.save(t);
    }

    // Para la Web Pública (Solo aprobados)
    public List<Testimonio> listarAprobados() {
        return repo.findByEstado(Testimonio.Estado.APROBADO);
    }
    
    // ADMIN: Listar todos (Pendientes, Aprobados, Rechazados)
    public List<Testimonio> listarTodos() {
        return repo.findAll();
    }

    // ADMIN: Cambiar estado
    public Testimonio cambiarEstado(int id, Testimonio.Estado nuevoEstado) {
        Testimonio t = repo.findById(id).orElseThrow(() -> new RuntimeException("Testimonio no encontrado"));
        t.setEstado(nuevoEstado);
        return repo.save(t);
    }

    // ADMIN: Eliminar
    public void eliminar(int id) {
        repo.deleteById(id);
    }
}