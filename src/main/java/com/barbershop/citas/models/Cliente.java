package com.barbershop.citas.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // <--- AGREGAR

import jakarta.persistence.*;

@Entity
@Table(name="Clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int idCliente;
	
	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apellidos;

	@Column(nullable = false, unique = true)
	private String dni;

	@Column(nullable = false)
	private String celular;
	
	@Column(nullable = true)
	private String correo;
	
	@OneToOne(fetch = FetchType.LAZY) // Es mejor usar LAZY
    @JoinColumn(name = "id_usuario", nullable = false) 
	@OnDelete(action=OnDeleteAction.CASCADE)
    // ESTO EVITA ERRORES AL TRAER EL USUARIO DENTRO DEL CLIENTE
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Usuario usuario;
	
	public Cliente() {}

	// Getters y Setters
	public int getIdCliente() { return idCliente; }
	public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

	public String getNombres() { return nombres; }
	public void setNombres(String nombres) { this.nombres = nombres; }

	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }

	public String getDni() { return dni; }
	public void setDni(String dni) { this.dni = dni; }

	public String getCelular() { return celular; }
	public void setCelular(String celular) { this.celular = celular; }

	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }

	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}