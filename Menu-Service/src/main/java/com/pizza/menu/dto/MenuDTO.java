package com.pizza.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates an all-args constructor
public class MenuDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
}
