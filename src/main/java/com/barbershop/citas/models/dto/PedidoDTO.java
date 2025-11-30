package com.barbershop.citas.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    private BigDecimal total;
    private String direccion;
    private int idUsuario;
    private List<DetalleDto> detalles;

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public List<DetalleDto> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleDto> detalles) { this.detalles = detalles; }

    public static class DetalleDto {
        private int idProducto;
        private int cantidad;
        private BigDecimal precioUnitario;

        public int getIdProducto() { return idProducto; }
        public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    }
}