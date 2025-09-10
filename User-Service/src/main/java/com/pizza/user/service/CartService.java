package com.pizza.user.service;

import com.pizza.user.client.MenuClient;
import com.pizza.user.dto.CartDTO;
import com.pizza.user.dto.CartItemDTO;
import com.pizza.user.dto.MenuDTO;
import com.pizza.user.entity.Cart;
import com.pizza.user.entity.CartItem;
import com.pizza.user.entity.User;
import com.pizza.user.repository.CartRepository;
import com.pizza.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MenuClient menuClient;

    public CartService(CartRepository cartRepository, UserRepository userRepository, MenuClient menuClient) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.menuClient = menuClient;
    }

    // Add item to cart
    public CartDTO addToCart(Long userId, Long itemId, int quantity) {
        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Cart newCart = Cart.builder()
                    .user(user)
                    .items(new ArrayList<>())
                    .totalAmount(0.0)
                    .build();
            return cartRepository.save(newCart);
        });

        // Check if item already exists in cart
        CartItem existingItem = cart.getItems().stream()
                .filter(i -> i.getMenuItemId().equals(itemId))
                .findFirst()
                .orElse(null);

        MenuDTO menu = menuClient.getMenuItemById(itemId);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setSubtotal(menu.getPrice() * existingItem.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .menuItemId(itemId)
                    .quantity(quantity)
                    .subtotal(menu.getPrice() * quantity)
                    .cart(cart)
                    .build();
            cart.getItems().add(newItem);
        }

        // Recalculate total
        cart.setTotalAmount(cart.getItems().stream().mapToDouble(CartItem::getSubtotal).sum());

        cartRepository.save(cart);

        return convertToDTO(cart);
    }

    // Get user cart
    public CartDTO getCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId).orElseGet(() ->
                Cart.builder()
                        .user(userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("User not found")))
                        .items(new ArrayList<>())
                        .totalAmount(0.0)
                        .build()
        );
        return convertToDTO(cart);
    }

    // Remove item from cart
    public CartDTO removeFromCart(Long userId, Long itemId) {
        Cart cart = cartRepository.findByUser_UserId(userId).orElse(null);
        if (cart != null) {
            cart.getItems().removeIf(i -> i.getMenuItemId().equals(itemId));
            cart.setTotalAmount(cart.getItems().stream().mapToDouble(CartItem::getSubtotal).sum());
            cartRepository.save(cart);
        }
        return convertToDTO(cart);
    }

    // Clear cart
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId).orElse(null);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }

    // Convert Cart entity to DTO
    private CartDTO convertToDTO(Cart cart) {
        List<CartItemDTO> itemsDTO = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            MenuDTO menu = menuClient.getMenuItemById(item.getMenuItemId());
            CartItemDTO dto = new CartItemDTO();
            dto.setId(item.getId());
            dto.setItemId(menu.getId());
            dto.setName(menu.getName());
            dto.setPrice(menu.getPrice());
            dto.setQuantity(item.getQuantity());
            dto.setSubtotal(item.getSubtotal());
            itemsDTO.add(dto);
        }

        double totalAmount = itemsDTO.stream().mapToDouble(CartItemDTO::getSubtotal).sum();
        return new CartDTO(cart.getId(), cart.getUser().getUserId(), itemsDTO, totalAmount);
    }
}
