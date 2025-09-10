package com.pizza.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;

    public MenuDTO(Long id, String name, String description, double price, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

}
