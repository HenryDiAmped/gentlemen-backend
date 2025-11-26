package com.barbershop.citas.models;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("id")
	private int idProducto;
	
	@Column(name = "nombre", length = 150, nullable = false, unique = true)
	@JsonProperty("name")
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	@JsonProperty("description")
	private String descripcion;
	
	@Column(name = "precio", precision = 5, scale = 2, nullable = false)
	@JsonProperty("price")
	private BigDecimal precio;
	
	@Column(name = "stock", nullable = false)
	private int stock;
	
	// --- NUEVOS CAMPOS (Necesarios para que se vea la imagen y marca) ---
    @Column(name = "imagen_url", length = 500)
    @JsonProperty("imageUrl") // Frontend espera "imageUrl"
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	@OnDelete(action=OnDeleteAction.NO_ACTION) //Regla de negocio
	private Categoria categoria;
	
	@JsonProperty("category")
    public String getCategoryName() {
        return (categoria != null) ? categoria.getNombre() : "Sin Categoría";
    }

	public Producto() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
	
