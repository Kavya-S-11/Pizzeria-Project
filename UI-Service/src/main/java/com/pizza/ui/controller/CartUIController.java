package com.pizza.ui.controller;

import com.pizza.ui.client.CartClient;
import com.pizza.ui.client.MenuClient;
import com.pizza.ui.dto.CartDTO;
import com.pizza.ui.dto.MenuDTO;
import com.pizza.ui.dto.UserDTO;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/cart")
public class CartUIController {

	  private final CartClient cartClient;
	    private final MenuClient menuClient; 

	    public CartUIController(CartClient cartClient, MenuClient menuClient) {
	        this.cartClient = cartClient;
	        this.menuClient = menuClient; 
	    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long itemId,
                            @RequestParam int quantity,
                            HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user"); // get user object
        if (user == null) {
        	System.out.println("User not in session!");
            return "redirect:/ui/users/login"; // not logged in
        }

        Long userId = user.getUserId(); // extract ID from user object
        MenuDTO menuItem;
        try {
            menuItem = menuClient.getMenuByIdforUser(itemId); // call user-facing endpoint
        } catch (Exception e) {
            System.out.println("Menu item not found: " + itemId);
            return "redirect:/ui/menus?error=ItemNotFound"; // redirect to menu page with error
        }

        if (menuItem == null) {
            System.out.println("Menu item not found: " + itemId);
            return "redirect:/ui/menus?error=ItemNotFound"; 
        }
        cartClient.addToCart(userId, itemId, quantity); 
        return "redirect:/ui/cart";
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/ui/users/login";
        }

        CartDTO cart = cartClient.getCart(user.getUserId()); 
        if (cart == null) {
            cart = new CartDTO(); 
        }

        Long userId = user.getUserId();
        model.addAttribute("cart", cart);
        return "cart"; // JSP page
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long itemId, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            cartClient.removeFromCart(user.getUserId(), itemId);
        }
        Long userId = user.getUserId();
        return "redirect:/ui/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            cartClient.clearCart(user.getUserId());
        }
        Long userId = user.getUserId();
        return "redirect:/ui/cart";
    }

}
