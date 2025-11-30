package com.barbershop.citas.models;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Convert;

@Entity
@Table(name="Barberos")
public class Barbero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_barbero")
	@JsonProperty("id")
	private int idBarbero;
	
	@JsonProperty("name")
	private String nombre;
	
	@ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	@JsonIgnoreProperties({"barberos", "empleados", "listaBarberos", "hibernateLazyInitializer", "handler"}) 
    @JsonProperty("locationId")
    private Sede sede;
	
	@Column(name = "rating", precision = 5, scale = 2, nullable = false)
	private BigDecimal rating;
	
	@Column(name = "imagen_url", length = 500)
    @JsonProperty("imageUrl")
	private String imagenUrl;
	
	@Column(length = 500)
	@JsonProperty("bio")
	private String biografia;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "work_schedule", columnDefinition = "TEXT") // Usamos TEXT para que soporte JSON largo
    @Convert(converter = WorkScheduleConverter.class)
    @JsonProperty("workSchedule") // Para que Jackson lo serialice bien al frontend
    private List<WorkSchedule> workSchedule;

	
	@Enumerated(EnumType.STRING)
	@Column(name = "day_off")
	private DayOff dayOff;

	@Enumerated(EnumType.STRING)
    private Shift shift;
	
	public enum DayOff {
	    LUNES,
	    MARTES,
	    MIERCOLES,
	    JUEVES,
	    VIERNES,
	    SABADO,
	    DOMINGO,
	    NINGUNO
	}
	
	public enum Shift {
	    FULL_TIME,
	    PART_TIME_MANANA,
	    PART_TIME_TARDE
	}

	public Barbero() {
	}

	public int getIdBarbero() {
		return idBarbero;
	}

	public void setIdBarbero(int idBarbero) {
		this.idBarbero = idBarbero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<WorkSchedule> getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(List<WorkSchedule> workSchedule) {
		this.workSchedule = workSchedule;
	}

	public DayOff getDayOff() {
		return dayOff;
	}

	public void setDayOff(DayOff dayOff) {
		this.dayOff = dayOff;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}
	

}
