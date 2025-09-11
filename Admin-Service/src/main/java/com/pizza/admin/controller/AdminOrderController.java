package com.pizza.admin.controller;

import com.pizza.admin.client.OrderClient;  // your Feign client
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ui/admin/orders")
public class AdminOrderController {

    private final OrderClient orderClient;

    public AdminOrderController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @PostMapping("/status/{orderId}")
    @ResponseBody
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status,
            @RequestParam(required = false) String message) {

        try {
            orderClient.updateStatus(orderId, status, message); // call order-service
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to update order status");
        }
    }
}
