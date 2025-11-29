package com.barbershop.citas.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Sedes")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sede")
	@JsonProperty("id")
	private int idSede;
	
	@Column(name = "nombre_sede", nullable = false, length = 45, unique = true)
	@JsonProperty("name")
	private String nombreSede;
	
	@Column(name = "direccion", nullable = false, length = 45, unique = true)
	@JsonProperty("address")
	private String direccion;
	
	@Column(name = "imagen_url", length = 500)
    @JsonProperty("imageUrl")
    private String imagenUrl;
	
	@Column(name = "mapa_link", length = 500)
    @JsonProperty("mapLink")
    private String mapaLink;
	
	@Column(name = "numero", length = 500)
    @JsonProperty("phone")
    private String numero;
	
	public Sede() {
		// TODO Auto-generated constructor stub
	}

	public int getIdSede() {
		return idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getMapaLink() {
		return mapaLink;
	}

	public void setMapaLink(String mapaLink) {
		this.mapaLink = mapaLink;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
