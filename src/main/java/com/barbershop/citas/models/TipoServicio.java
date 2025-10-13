package com.barbershop.citas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tipo_Servicios")
public class TipoServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_servicio")
	private int idTipoServicio;
	
	@Column(name = "nombre", length = 45, nullable = false, unique = true)
	private String nombre;
	
	public TipoServicio() {
		// TODO Auto-generated constructor stub
	}

	public int getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
