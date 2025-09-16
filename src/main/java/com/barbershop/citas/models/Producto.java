package com.barbershop.citas.models;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int idProducto;
	
	@Column(name = "nombre", length = 45, nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "precio", precision = 5, scale = 2, nullable = false)
	private BigDecimal precio;
	
	@Column(name = "stock", nullable = false)
	private int stock;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	private Categoria categoria;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
