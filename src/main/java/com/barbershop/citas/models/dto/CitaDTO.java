package com.barbershop.citas.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class CitaDTO implements Serializable {

    private String fecha;
    private String hora;
    private String codigoConfirmacion;

    @JsonProperty("cliente") 
    private UsuarioIdDTO cliente;

    @JsonProperty("barbero")
    private BarberoIdDTO barbero;

    @JsonProperty("servicio")
    private ServicioIdDTO servicio;

    @JsonProperty("sede")
    private SedeIdDTO sede;

    public static class UsuarioIdDTO {
        public int idUsuario;
    }

    public static class BarberoIdDTO {
        public int idBarbero;
    }

    public static class ServicioIdDTO {
        public int idServicio;
    }
    
    public static class SedeIdDTO {
        public int idSede;
    }

    // --- GETTERS Y SETTERS ---
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getCodigoConfirmacion() { return codigoConfirmacion; }
    public void setCodigoConfirmacion(String codigoConfirmacion) { this.codigoConfirmacion = codigoConfirmacion; }

    public UsuarioIdDTO getCliente() { return cliente; }
    public void setCliente(UsuarioIdDTO cliente) { this.cliente = cliente; }

    public BarberoIdDTO getBarbero() { return barbero; }
    public void setBarbero(BarberoIdDTO barbero) { this.barbero = barbero; }

    public ServicioIdDTO getServicio() { return servicio; }
    public void setServicio(ServicioIdDTO servicio) { this.servicio = servicio; }
    
    public SedeIdDTO getSede() { return sede; }
    public void setSede(SedeIdDTO sede) { this.sede = sede; }
}