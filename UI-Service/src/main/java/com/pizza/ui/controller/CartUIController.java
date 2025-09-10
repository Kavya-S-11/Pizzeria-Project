package com.pizza.ui.controller;

import com.pizza.ui.client.CartClient;
import com.pizza.ui.dto.CartDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/cart")
public class CartUIController {

    private final CartClient cartClient;

    public CartUIController(CartClient cartClient) {
        this.cartClient = cartClient;
    }

    // Add item to cart
    @PostMapping("/add")
    public String addToCart(@RequestParam Long itemId,
                            @RequestParam int quantity,
                            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId"); // Get logged-in user ID
        if (userId == null) {
            return "redirect:/login"; // redirect to login if not logged in
        }

        cartClient.addToCart(userId, itemId, quantity); // call User-Service
        return "redirect:/ui/cart";
    }

    // View user cart
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        CartDTO cart = cartClient.getCart(userId); // fetch cart from User-Service
        if (cart == null) {
            cart = new CartDTO(); // empty cart if null
        }

        model.addAttribute("cart", cart);
        return "cart"; // JSP page = cart.jsp
    }

    // Remove item from cart
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long itemId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            cartClient.removeFromCart(userId, itemId);
        }
        return "redirect:/ui/cart";
    }

    // Clear cart
    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            cartClient.clearCart(userId);
        }
        return "redirect:/ui/cart";
    }
}
