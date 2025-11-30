package com.barbershop.citas.models;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password; // IMPORTANTE: Debe llamarse 'password' para coincidir con Angular
    private String phone;
    
    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    
    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDni(String dni) { this.dni = dni; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
}