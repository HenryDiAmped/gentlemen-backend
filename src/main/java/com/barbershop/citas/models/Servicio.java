package com.barbershop.citas.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Servicios")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private int idServicio;
	
	@Column(name = "nombre", length = 45, nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "detalle", length = 255, nullable = false, unique = true)
	private String detalle;
	
	@Column(name = "tarifa", precision = 5, scale = 2, nullable = false)
	private BigDecimal tarifa;
	
	public Servicio() {
		// TODO Auto-generated constructor stub
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}
}
