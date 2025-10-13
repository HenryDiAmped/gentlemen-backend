package com.barbershop.citas.models;

import java.time.LocalDate;
import java.time.LocalTime;

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
import jakarta.persistence.Table;

@Entity
@Table(name="Horarios_Atencion")
public class HorariosAtencion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_horario_atencion")
	private int idHorarioAtencion;
	
	@Column(name = "hora", nullable = false)
	private LocalTime hora;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private Estado estado;
	
	public enum Estado {
		FERIADO,
		RESERVADO,
		OTRO
	}
	
	@ManyToOne
	@JoinColumn(name="id_barbero", nullable=false)
	@OnDelete(action= OnDeleteAction.NO_ACTION) //Regla de negocio
	private Barbero barbero;
	
	public HorariosAtencion() {
		// TODO Auto-generated constructor stub
	}

	public int getIdHorarioAtencion() {
		return idHorarioAtencion;
	}

	public void setIdHorarioAtencion(int idHorarioAtencion) {
		this.idHorarioAtencion = idHorarioAtencion;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Barbero getBarbero() {
		return barbero;
	}

	public void setBarbero(Barbero barbero) {
		this.barbero = barbero;
	}
}
