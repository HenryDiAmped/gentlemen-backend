package com.barbershop.citas.models.dto;

import java.time.LocalDate;

public class CitaDTO {
    private LocalDate date;      
    private String time;         
    private BarberData barber;   
    private ServiceData service; 
    private UserData user;
    
    // --- NUEVO: Recibe la Sede desde Angular ---
    // Angular envía: "location": { "id": 1 }
    private LocationData location; 
    
    private String confirmationNumber;

    // Clases internas para mapear el JSON
    public static class BarberData { public int id; }
    public static class ServiceData { public int id; }
    
    // Clase interna para la Sede (aunque se llame LocationData por el frontend)
    public static class LocationData { public int id; } 

    public static class UserData {
        public Integer id; 
        public String firstName;
        public String lastName;
        public String email;
        public String phone;
        public String dni;
    }

    // Getters y Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    
    public BarberData getBarber() { return barber; }
    public void setBarber(BarberData barber) { this.barber = barber; }
    
    public ServiceData getService() { return service; }
    public void setService(ServiceData service) { this.service = service; }
    
    public UserData getUser() { return user; }
    public void setUser(UserData user) { this.user = user; }
    
    // Getter y Setter para Location (Sede)
    public LocationData getLocation() { return location; }
    public void setLocation(LocationData location) { this.location = location; }
    
    public String getConfirmationNumber() { return confirmationNumber; }
    public void setConfirmationNumber(String confirmationNumber) { this.confirmationNumber = confirmationNumber; }
}