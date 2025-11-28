package com.barbershop.citas.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class WorkSchedule {
	private String day;     // Ej: "Lunes"
    private String start;   // Ej: "09:00"
    private String end;     // Ej: "17:00"

    public WorkSchedule() {}

    public WorkSchedule(String day, String start, String end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
