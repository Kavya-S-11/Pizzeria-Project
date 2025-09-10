package com.pizza.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@Data
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private Double price;
    private Boolean available = true;

}
