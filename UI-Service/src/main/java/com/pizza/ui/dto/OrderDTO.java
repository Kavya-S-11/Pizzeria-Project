package com.pizza.ui.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private double totalAmount;
    private String status;
    private String deliveryMode;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> items; // <-- here
    private String adminMessage;
}
