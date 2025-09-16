package com.barbershop.citas.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Citas")
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita")
	private int idCita;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private Estado estado;
	
	public enum Estado {
	    ATENDIDO,
	    POR_ATENDER
	}
	
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE) //Regla de negocio
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="id_horario_atencion", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	private HorariosAtencion horariosAtencion;
	
	@OneToOne
	@JoinColumn(name="id_comentario")
	@OnDelete(action=OnDeleteAction.SET_NULL) //Regla de negocio
	private Comentario comentario;
	
	@ManyToOne
	@JoinColumn(name="id_servicio", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	private Servicio servicio;
	
	public Cita() {
		// TODO Auto-generated constructor stub
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public HorariosAtencion getHorariosAtencion() {
		return horariosAtencion;
	}

	public void setHorariosAtencion(HorariosAtencion horariosAtencion) {
		this.horariosAtencion = horariosAtencion;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
}
