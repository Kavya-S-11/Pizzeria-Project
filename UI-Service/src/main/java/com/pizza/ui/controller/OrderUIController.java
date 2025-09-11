package com.pizza.ui.controller;

import com.pizza.ui.client.CartClient;
import com.pizza.ui.client.OrderClient;
import com.pizza.ui.dto.CartDTO;
import com.pizza.ui.dto.CartItemDTO;
import com.pizza.ui.dto.OrderDTO;
import com.pizza.ui.dto.OrderItemDTO;
import com.pizza.ui.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/orders")
public class OrderUIController {
	
	private final CartClient cartClient;

    private final OrderClient orderClient;

    public OrderUIController(OrderClient orderClient,CartClient cartClient) {
        this.cartClient = cartClient;
		this.orderClient = orderClient;
    }

    @PostMapping("/place")
    public String placeOrder(
            @RequestParam(required = false, defaultValue = "Pickup") String deliveryMode,
            HttpSession session,
            Model model) {
    	System.out.println("=== placeOrder called ===");
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
        	System.out.println("User not in session, redirecting to login");
            return "redirect:/ui/users/login";
        }

        Long userId = user.getUserId();
        System.out.println("User ID from session: " + userId);

        // fetch cart directly from backend
        CartDTO cart = cartClient.getCart(userId);
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
        	 System.out.println("Cart is empty for user: " + userId);
            model.addAttribute("message", "Your cart is empty.");
            return "cart";
        }

        System.out.println("Cart fetched, total items: " + cart.getItems().size());
        // build order
        OrderDTO order = new OrderDTO();
        order.setUserId(userId);
        order.setTotalAmount(cart.getTotalAmount());
        order.setDeliveryMode(deliveryMode);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItemDTO> items = new ArrayList<>();
        for (CartItemDTO ci : cart.getItems()) {
            OrderItemDTO oi = new OrderItemDTO();
            oi.setItemId(ci.getItemId());
            oi.setName(ci.getName());
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            oi.setSubtotal(ci.getSubtotal());
            items.add(oi);
        }
        order.setItems(items);

        System.out.println("Order DTO prepared, items count: " + order.getItems().size());
        try {
            OrderDTO saved = orderClient.placeOrder(order);
            System.out.println("Order placed successfully: " + saved.getUserId());
            cartClient.clearCart(userId); // clear backend cart
            model.addAttribute("order", saved);
            return "orderSuccess";
 // shows orderSuccess.jsp
        } catch (Exception ex) {
        	System.out.println("Exception while placing order: " + ex.getMessage());
            ex.printStackTrace();
            model.addAttribute("error", "Failed to place order: " + ex.getMessage());
            return "cart";
        }
    }

    
    @GetMapping("/my")
    public String myOrders(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");  // get the UserDTO
        if (user == null) return "redirect:/ui/users/login";

        Long userId = user.getUserId();  // extract the userId
        List<OrderDTO> orders = orderClient.getUserOrders(userId);
        model.addAttribute("orders", orders);
        return "myOrders";
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user"); // get UserDTO from session
        if (user == null) return "redirect:/ui/users/login";

        Long userId = user.getUserId(); // extract userId
        orderClient.cancelOrder(orderId);
        return "redirect:/ui/orders/my"; // refresh orders page
    }


    @PostMapping("/pay/{orderId}")
    public String payOrder(@PathVariable Long orderId, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user"); // get UserDTO from session
        if (user == null) return "redirect:/ui/users/login";

        Long userId = user.getUserId(); // extract userId
        orderClient.payOrder(orderId);
        return "redirect:/ui/orders/my"; // refresh orders page
    }


}
