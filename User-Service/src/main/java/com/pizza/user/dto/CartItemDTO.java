package com.pizza.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;       // CartItem ID from DB
    private Long itemId;   // menuItemId
    private String name;   // menu item name
    private double price;  
    private int quantity;  
    private double subtotal;
}
