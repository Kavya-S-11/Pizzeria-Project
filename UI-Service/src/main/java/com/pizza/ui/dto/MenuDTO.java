package com.pizza.ui.dto;


import lombok.Data;

@Data
public class MenuDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private String imageUrl;

}
