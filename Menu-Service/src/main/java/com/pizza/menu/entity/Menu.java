package com.pizza.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Data
@Builder
@NoArgsConstructor      
@AllArgsConstructor     
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private double price;
    private String imageUrl;

}
