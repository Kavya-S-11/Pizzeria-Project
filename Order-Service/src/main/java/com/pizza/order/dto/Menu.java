package com.pizza.order.dto;

public class Menu {

    private Long id;
    private String itemName;
    private String description;
    private double price;
    private boolean available;

    // Constructors
    public Menu() {}

    public Menu(Long id, String itemName, String description, double price, boolean available) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
