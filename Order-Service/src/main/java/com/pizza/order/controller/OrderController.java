package com.pizza.order.controller;

import com.pizza.order.dto.OrderDTO;
import com.pizza.order.entity.Order.OrderStatus;
import com.pizza.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // User: place order
    @PostMapping("/place")
    public OrderDTO placeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(orderDTO); // already returns DTO
    }

    // User: cancel order
    @PutMapping("/cancel/{id}")
    public OrderDTO cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id); // returns DTO
    }

    // Admin: update status (with optional admin message)
    @PutMapping("/status/{id}")
    public OrderDTO updateStatus(@PathVariable Long id,
                                 @RequestParam OrderStatus status,
                                 @RequestParam(required = false) String message) {
        return orderService.updateStatus(id, status, message); 
    }

    // User: view my orders
    @GetMapping("/user/{userId}")
    public List<OrderDTO> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    // Admin: view all orders
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders(); 
    }

    // Admin: get revenue
    @GetMapping("/revenue")
    public double getMonthlyRevenue() {
        return orderService.getMonthlyRevenue();
    }
    
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PutMapping("/{id}/status")
    public void updateOrderStatus(@PathVariable Long id,
                                  @RequestParam String status,
                                  @RequestParam String paymentMode) {
        orderService.updateOrderStatus(id, status, paymentMode);
    }

}
