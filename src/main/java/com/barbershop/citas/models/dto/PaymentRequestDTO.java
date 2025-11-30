package com.barbershop.citas.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentRequestDTO {
    private List<ItemDto> items;
    private BigDecimal shippingCost;

    // Getters y Setters
    public List<ItemDto> getItems() { return items; }
    public void setItems(List<ItemDto> items) { this.items = items; }
    public BigDecimal getShippingCost() { return shippingCost; }
    public void setShippingCost(BigDecimal shippingCost) { this.shippingCost = shippingCost; }

    public static class ItemDto {
        private String name;
        private int quantity;
        private BigDecimal price;

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
    }
}