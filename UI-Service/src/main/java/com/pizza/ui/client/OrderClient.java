package com.pizza.ui.client;

import com.pizza.ui.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderClient {

    @PostMapping("/orders/place")
    OrderDTO placeOrder(@RequestBody OrderDTO order);

    @GetMapping("/orders/user/{userId}")
    List<OrderDTO> getUserOrders(@PathVariable Long userId);

    @GetMapping("/orders")
    List<OrderDTO> getAllOrders();
    
    @PutMapping("/orders/cancel/{id}")
    OrderDTO cancelOrder(@PathVariable("id") Long orderId);

    @PutMapping("/orders/status/{id}")
    OrderDTO updateStatus(@PathVariable Long id, @RequestParam String status);
    
    @PutMapping("/orders/pay/{id}")
    OrderDTO payOrder(@PathVariable("id") Long id);

}
