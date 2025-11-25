package com.barbershop.citas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;
	
	@Column(name = "nombres", nullable = false, length = 45)
	private String nombres;
	
	@Column(name = "apellidos", nullable = false, length = 45)
	private String apellidos;
	
	@Column(name = "dni", nullable = false, length = 20, unique = true)
	private String dni;
	
	@Column(name = "email", length = 100, unique = true)
	private String email;
	
	@Column(name = "celular", nullable = false, length = 9, unique = true)
	private String celular;
	
	@Column(name = "contrasena", nullable = false, length = 255)
	private String contrasena;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_usuario", nullable = false)
	private TipoUsuario tipoUsuario;
	
	public enum TipoUsuario {
	    ADMINISTRADOR,
	    CLIENTE
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
