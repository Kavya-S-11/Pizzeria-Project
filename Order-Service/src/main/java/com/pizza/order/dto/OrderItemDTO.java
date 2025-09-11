package com.pizza.order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long itemId;     // ID of the menu item
    private String name;     // Item name
    private double price;    // Price per unit
    private int quantity;    // Quantity ordered
    private double subtotal; // price * quantity
}
