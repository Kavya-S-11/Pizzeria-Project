package com.pizza.ui.controller;

import com.pizza.ui.client.MenuClient;
import com.pizza.ui.dto.MenuDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenuUIController {

    private final MenuClient menuClient;

    public MenuUIController(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

    @GetMapping("/ui/menus")
    public String getMenus(Model model) {
        List<MenuDTO> menus = menuClient.getAllMenus();
        model.addAttribute("menus", menus);
        return "menus";  
    }
}
