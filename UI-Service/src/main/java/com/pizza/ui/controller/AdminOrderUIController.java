package com.pizza.ui.controller;

import com.pizza.ui.dto.OrderDTO;
import com.pizza.ui.client.OrderClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/admin/orders")
public class AdminOrderUIController {

    private final OrderClient orderClient;

    public AdminOrderUIController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    // View pending orders
    @GetMapping("/pending")
    public String pendingOrders(Model model) {
        List<OrderDTO> orders = orderClient.getAllOrders(); // You can filter PENDING in backend if needed
        orders.removeIf(o -> !o.getStatus().equals("PENDING")); // Only show pending
        model.addAttribute("orders", orders);
        return "adminOrders"; // JSP page
    }

    // Accept / Reject order
    @PostMapping("/status/{orderId}")
    public String updateOrderStatus(@PathVariable Long orderId,
                                    @RequestParam String status,
                                    @RequestParam(required = false) String message) {

        orderClient.updateStatus(orderId, status, message);
        return "redirect:/ui/admin/orders/pending"; // Refresh page
    }
}
