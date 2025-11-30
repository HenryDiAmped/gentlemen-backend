package com.barbershop.citas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "guia_pasos")
public class GuiaPaso {
    
    @Id
    @Column(name = "paso_key", length = 20)
    private String pasoKey; // Ej: 'step1', 'contact'

    private String title;
    private String description;
    
    @Column(name = "whatsapp_number")
    private String whatsappNumber;
    
    @Column(name = "whatsapp_link")
    private String whatsappLink;

    public GuiaPaso() {}

    public GuiaPaso(String pasoKey, String title, String description, String whatsappNumber, String whatsappLink) {
        this.pasoKey = pasoKey;
        this.title = title;
        this.description = description;
        this.whatsappNumber = whatsappNumber;
        this.whatsappLink = whatsappLink;
    }

    // Getters y Setters
    public String getPasoKey() { return pasoKey; }
    public void setPasoKey(String pasoKey) { this.pasoKey = pasoKey; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getWhatsappNumber() { return whatsappNumber; }
    public void setWhatsappNumber(String whatsappNumber) { this.whatsappNumber = whatsappNumber; }
    public String getWhatsappLink() { return whatsappLink; }
    public void setWhatsappLink(String whatsappLink) { this.whatsappLink = whatsappLink; }
}