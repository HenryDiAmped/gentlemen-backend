package com.barbershop.citas.models;

import java.util.List;
import jakarta.persistence.Embeddable;
// Asegúrate de tener las importaciones correctas para ElementCollection si decides mapearlo así, 
// o usa un Converter si prefieres guardarlo como JSON string en la BD.

@Embeddable
public class WorkSchedule {
    private String day;      // Ej: "Lunes"
    private List<String> hours; // Ej: ["09:00", "10:00", "11:00"]

    public WorkSchedule() {}

    public WorkSchedule(String day, List<String> hours) {
        this.day = day;
        this.hours = hours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }
}