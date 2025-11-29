package com.barbershop.citas.models;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Servicios")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	@JsonProperty("id")
	private int idServicio;
	
	@Column(name = "nombre", length = 150, nullable = false, unique = true)
	@JsonProperty("name")
	private String nombre;
	
	@Column(name = "detalle", length = 255, nullable = false)
	@JsonProperty("description")
	private String detalle;
	
	@Column(name = "tarifa", precision = 5, scale = 2, nullable = false)
	@JsonProperty("price")
	private BigDecimal tarifa;
	
	@Column(name = "duracion")
	@JsonProperty("duration")
	private int duracion;
	
	@Column(name = "imagen_url", length = 500)
    @JsonProperty("imageUrl")
    private String imagenUrl;
	
	@ManyToOne
    @JoinColumn(name = "id_tipo_servicio", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	@JsonIgnore
    private TipoServicio tipoServicio;
	
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

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
}
