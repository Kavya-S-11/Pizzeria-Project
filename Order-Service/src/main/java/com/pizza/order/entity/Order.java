package com.pizza.order.entity;

import jakarta.persistence.*;
import java.util.List;

import com.pizza.order.dto.Menu;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;   // user placing the order
    private String status; // PENDING, CONFIRMED, DELIVERED

    @ManyToMany
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> items;

    // Constructors
    public Order() {}

    public Order(Long userId, String status, List<Menu> items) {
        this.userId = userId;
        this.status = status;
        this.items = items;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Menu> getItems() { return items; }
    public void setItems(List<Menu> items) { this.items = items; }
}
