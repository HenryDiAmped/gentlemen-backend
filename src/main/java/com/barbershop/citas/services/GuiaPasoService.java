package com.barbershop.citas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.citas.models.GuiaPaso;
import com.barbershop.citas.repositorys.GuiaPasoRepository;

@Service
public class GuiaPasoService {

    @Autowired
    private GuiaPasoRepository repository;

    public List<GuiaPaso> obtenerTodos() {
        List<GuiaPaso> lista = repository.findAll();
        if (lista.isEmpty()) {
            return inicializarDatosPorDefecto();
        }
        return lista;
    }

    public GuiaPaso actualizarPaso(String key, GuiaPaso datos) {
        GuiaPaso existente = repository.findById(key)
            .orElse(new GuiaPaso(key, "", "", null, null));
        
        existente.setTitle(datos.getTitle());
        existente.setDescription(datos.getDescription());
        existente.setWhatsappNumber(datos.getWhatsappNumber());
        existente.setWhatsappLink(datos.getWhatsappLink());
        
        return repository.save(existente);
    }

    private List<GuiaPaso> inicializarDatosPorDefecto() {
        // Datos por defecto (Misma lógica que tenías en el Frontend)
        List<GuiaPaso> defaults = List.of(
            new GuiaPaso("step1", "Seleccionar local", "Selecciona el local de tu reserva", null, null),
            new GuiaPaso("step2", "Selecciona tu servicio", "Seleccione un servicio...", null, null),
            new GuiaPaso("step3", "Selecciona al barbero", "Elige un barbero...", null, null),
            new GuiaPaso("step4", "Selecciona fecha & hora", "Haga clic en una fecha...", null, null),
            new GuiaPaso("step5", "Ingresa tu información", "Inicia sesión o crea una cuenta...", null, null),
            new GuiaPaso("step6", "Confirmación", "Su cita se ha programado correctamente.", null, null),
            new GuiaPaso("contact", "¿Consultas?", "Escríbenos al WhatsApp", "995 515 022", "https://wa.me/51995515022")
        );
        return repository.saveAll(defaults);
    }
}