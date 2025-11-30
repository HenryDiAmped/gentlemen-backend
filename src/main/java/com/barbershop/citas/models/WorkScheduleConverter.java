package com.barbershop.citas.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Converter
public class WorkScheduleConverter implements AttributeConverter<List<WorkSchedule>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<WorkSchedule> attribute) {
        if (attribute == null) return null; // Guardar NULL si no hay datos
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            System.err.println("❌ ERROR escribiendo JSON a BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<WorkSchedule> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            // INTENTAMOS LEER EL JSON
            return objectMapper.readValue(dbData, new TypeReference<List<WorkSchedule>>() {});
        } catch (IOException e) {
            // AQUÍ ESTÁ EL CAMBIO: Imprimir el error en la consola de Java
            System.err.println("❌ ERROR LEYENDO JSON DE BD: " + e.getMessage());
            System.err.println("👉 DATA QUE FALLÓ: " + dbData);
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
}