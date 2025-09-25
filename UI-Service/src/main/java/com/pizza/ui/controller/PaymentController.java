package com.pizza.ui.controller;

import com.pizza.ui.client.OrderClient;   // Feign client to call order-service
import com.pizza.ui.dto.OrderDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/orders")
public class PaymentController {

    private final OrderClient orderClient;

    public PaymentController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    // Show payment page
    @PostMapping("/pay/{orderId}")
    public String payOrder(@PathVariable Long orderId, Model model) {
        OrderDTO order = orderClient.getOrderById(orderId);
        model.addAttribute("order", order);
        return "paymentPage";
    }

    @PostMapping("/confirmPayment/{orderId}")
    public String confirmPayment(@PathVariable Long orderId,
                                 @RequestParam String paymentMode) {

        orderClient.updateOrderStatus(orderId, "COMPLETED", paymentMode);
        return "redirect:/ui/orders/my";
    }

}
