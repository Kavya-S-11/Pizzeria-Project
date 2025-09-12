package com.pizza.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;               
    private Long userId;
    private List<CartItemDTO> items;
    private double totalAmount;
}
