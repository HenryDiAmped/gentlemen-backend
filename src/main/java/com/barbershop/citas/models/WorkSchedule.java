package com.barbershop.citas.models;

import java.io.Serializable;
import java.util.List;

public class WorkSchedule implements Serializable {
    
    private String day;
    private List<String> hours;

    public WorkSchedule() {} 

    public WorkSchedule(String day, List<String> hours) {
        this.day = day;
        this.hours = hours;
    }

    // Getters y Setters OBLIGATORIOS
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public List<String> getHours() { return hours; }
    public void setHours(List<String> hours) { this.hours = hours; }
}