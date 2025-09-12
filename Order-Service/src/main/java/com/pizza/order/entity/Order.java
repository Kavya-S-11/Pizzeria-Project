package com.pizza.order.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId; // ID of the user placing the order

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, ACCEPTED, REJECTED, CANCELLED

    private String deliveryMode; //  "Home Delivery" or "Pickup"

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items=new ArrayList<>(); // List of items in the order

    private String adminMessage; // message from admin about order status
    
    private String paymentMode;
    
    public enum OrderStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        CANCELLED,
        COMPLETED
    }
}



