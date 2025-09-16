package com.barbershop.citas.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private int idPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private Estado estado;
	
	public enum Estado {
		PAGADO,
		PENDIENTE_PAGO
	}
	
	@Column(name = "total", precision = 5, scale = 2, nullable = false)
	private BigDecimal total;
	
	@Column(name = "subtotal", precision = 5, scale = 2, nullable = false)
	private BigDecimal subtotal;
	
	@Column(name = "fecha_pedido", nullable = false)
	private LocalDate fechaPedido;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
}
