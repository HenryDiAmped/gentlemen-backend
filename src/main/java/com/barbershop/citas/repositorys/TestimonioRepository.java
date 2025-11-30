package com.barbershop.citas.repositorys;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.barbershop.citas.models.Testimonio;

public interface TestimonioRepository extends JpaRepository<Testimonio, Integer> {
    // Para mostrar en el Home solo los aprobados
    List<Testimonio> findByEstado(Testimonio.Estado estado);
}