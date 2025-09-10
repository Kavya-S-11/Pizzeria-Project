package com.pizza.user.controller;

import com.pizza.user.dto.CartDTO;
import com.pizza.user.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add item to cart
    @PostMapping("/{userId}/add")
    public ResponseEntity<CartDTO> addToCart(
            @PathVariable Long userId,
            @RequestParam Long itemId,
            @RequestParam int quantity) {

        CartDTO cartDTO = cartService.addToCart(userId, itemId, quantity);
        return ResponseEntity.ok(cartDTO);
    }

    // Get user’s cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId) {
        CartDTO cartDTO = cartService.getCart(userId);
        return ResponseEntity.ok(cartDTO);
    }

    // Remove item from cart
    @DeleteMapping("/{userId}/remove/{itemId}")
    public ResponseEntity<CartDTO> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long itemId) {

        CartDTO cartDTO = cartService.removeFromCart(userId, itemId);
        return ResponseEntity.ok(cartDTO);
    }

    // Clear cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
