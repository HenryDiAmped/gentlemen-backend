package com.barbershop.citas.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Barberos")
public class Barbero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_barbero")
	private int idBarbero;
	
	@OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE) //Regla de negocio
    private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
    private Sede sede;
	
	public Barbero() {
		// TODO Auto-generated constructor stub
	}

	public int getIdBarbero() {
		return idBarbero;
	}

	public void setIdBarbero(int idBarbero) {
		this.idBarbero = idBarbero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
