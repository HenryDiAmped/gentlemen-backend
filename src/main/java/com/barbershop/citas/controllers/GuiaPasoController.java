package com.barbershop.citas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.barbershop.citas.models.GuiaPaso;
import com.barbershop.citas.services.GuiaPasoService;

@RestController
@RequestMapping("/api/v1/content")
@CrossOrigin(origins = "http://localhost:4200")
public class GuiaPasoController {

    @Autowired
    private GuiaPasoService service;

    @GetMapping
    public List<GuiaPaso> listarContenido() {
        return service.obtenerTodos();
    }

    @PutMapping("/{key}")
    public GuiaPaso actualizarContenido(@PathVariable String key, @RequestBody GuiaPaso paso) {
        return service.actualizarPaso(key, paso);
    }
}