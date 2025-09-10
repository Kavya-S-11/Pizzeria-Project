package com.pizza.ui.client;

import com.pizza.ui.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface CartClient {

    @PostMapping("/cart/{userId}/add")
    void addToCart(@PathVariable("userId") Long userId,
                   @RequestParam("itemId") Long itemId,
                   @RequestParam("quantity") int quantity);

    @GetMapping("/cart/{userId}")
    CartDTO getCart(@PathVariable("userId") Long userId);

    @DeleteMapping("/cart/{userId}/remove/{itemId}")
    void removeFromCart(@PathVariable("userId") Long userId,
                        @PathVariable("itemId") Long itemId);

    @DeleteMapping("/cart/{userId}/clear")
    void clearCart(@PathVariable("userId") Long userId);
}
