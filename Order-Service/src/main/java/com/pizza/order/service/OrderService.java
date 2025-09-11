package com.pizza.order.service;

import com.pizza.order.dto.OrderDTO;
import com.pizza.order.dto.OrderItemDTO;
import com.pizza.order.entity.Order;
import com.pizza.order.entity.Order.OrderStatus;
import com.pizza.order.entity.OrderItem;
import com.pizza.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Place order
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setDeliveryMode(orderDTO.getDeliveryMode());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> items = orderDTO.getItems().stream().map(i -> {
            OrderItem oi = new OrderItem();
            oi.setItemId(i.getItemId());
            oi.setName(i.getName());
            oi.setPrice(i.getPrice());
            oi.setQuantity(i.getQuantity());
            oi.setSubtotal(i.getSubtotal());
            oi.setOrder(order);
            return oi;
        }).collect(Collectors.toList());
        order.setItems(items);

        Order saved = orderRepository.save(order);
        return mapToDTO(saved);
    }

    // Cancel order
    public OrderDTO cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.CANCELLED);
        Order saved = orderRepository.save(order);
        return mapToDTO(saved);
    }

    // Admin: accept/reject
    public OrderDTO updateStatus(Long id, OrderStatus status, String adminMessage) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        order.setAdminMessage(adminMessage);
        Order saved = orderRepository.save(order);
        return mapToDTO(saved);
    }

    // Get user orders
    public List<OrderDTO> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Admin: calculate monthly revenue (only ACCEPTED or COMPLETED)
    public double getMonthlyRevenue() {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.ACCEPTED || o.getStatus() == OrderStatus.COMPLETED)
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    // Helper method to map entity to DTO
    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDeliveryMode(order.getDeliveryMode());
        dto.setStatus(order.getStatus().name());
        dto.setOrderDate(order.getOrderDate());

        List<OrderItemDTO> itemDTOs = order.getItems().stream().map(oi -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setItemId(oi.getItemId());
            itemDTO.setName(oi.getName());
            itemDTO.setPrice(oi.getPrice());
            itemDTO.setQuantity(oi.getQuantity());
            itemDTO.setSubtotal(oi.getSubtotal());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }
}
