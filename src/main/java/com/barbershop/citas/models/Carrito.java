package com.barbershop.citas.models;

import java.time.LocalDate;

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
@Table(name="Carritos")
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrito")
	private int idCarrito;
	
	@Column(name = "fecha_creacion", nullable = false)
	private LocalDate fechaCreacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private Estado estado;
	
	public enum Estado {
		APROBADO,
		NO_APROBADO
	}
	
	@OneToOne
	@JoinColumn(name = "id_producto", nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE) //Regla de negocio
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE) //Regla de negocio
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	@OnDelete(action=OnDeleteAction.CASCADE) //Regla de negocio
	private Cliente cliente;
	
	public Carrito() {
		// TODO Auto-generated constructor stub
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
