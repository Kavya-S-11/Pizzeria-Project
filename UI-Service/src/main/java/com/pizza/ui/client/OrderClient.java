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

   
//    @PutMapping("/orders/pay/{id}")
//    OrderDTO payOrder(@PathVariable("id") Long id);
//    
    @PutMapping("/orders/status/{id}")
    void updateStatus(@PathVariable("id") Long orderId,
                      @RequestParam("status") String status,
                      @RequestParam(value = "message", required = false) String message);

    @GetMapping("/orders/{id}")
    OrderDTO getOrderById(@PathVariable("id") Long orderId);

    // Update order status (with payment mode)
    @PutMapping("/orders/{id}/status")
    void updateOrderStatus(@PathVariable("id") Long orderId,
                           @RequestParam("status") String status,
                           @RequestParam("paymentMode") String paymentMode);

}
