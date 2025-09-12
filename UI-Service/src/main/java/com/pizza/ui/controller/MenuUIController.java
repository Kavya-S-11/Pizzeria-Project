package com.pizza.ui.controller;

import com.pizza.ui.client.MenuClient;
import com.pizza.ui.dto.MenuDTO;
import com.pizza.ui.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class MenuUIController {

    private final MenuClient menuClient;

    public MenuUIController(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

 // List menu items
    @GetMapping("/ui/admin/menu")
    public String listMenuItems(Model model) {
        List<MenuDTO> menus = menuClient.getAllMenus();
        model.addAttribute("menuList", menus);
        return "adminMenu"; 
    }

    // Show add menu page
    @GetMapping("/ui/admin/menu/add")
    public String addMenuPage(Model model) {
        model.addAttribute("menuItem", new MenuDTO());
        return "adminAddMenu";
    }

    // Show edit menu page
    @GetMapping("/ui/admin/menu/edit/{id}")
    public String editMenuPage(@PathVariable Long id, Model model) {
        MenuDTO item = menuClient.getMenuById(id);
        model.addAttribute("menuItem", item);
        return "adminEditMenu";
    }
    
    @GetMapping("/ui/menus")
    public String getMenus(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/ui/users/login"; // redirect to login if session expired
        }

        List<MenuDTO> menus = menuClient.getAllMenus();
        model.addAttribute("menuList", menus);

        model.addAttribute("user", user);

        return "menus";  
    }

    @PostMapping("/ui/admin/menu/add")
    public String addMenu(@ModelAttribute MenuDTO menuItem) {
        menuClient.addMenu(menuItem); 
        return "redirect:/ui/admin/menu"; 
    }
    @PostMapping("/ui/admin/menu/edit/{id}")
    public String editMenu(@PathVariable Long id, @ModelAttribute MenuDTO menuItem) {
        menuClient.editMenu(id, menuItem);
        return "redirect:/ui/admin/menu";
    }
    
    @PostMapping("/ui/admin/menu/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuClient.deleteMenu(id);
        return "redirect:/ui/admin/menu";
    }
    
    @GetMapping("/menus/{id}")
    public String viewMenuItem(@PathVariable Long id, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/ui/users/login";
        }

        MenuDTO item = menuClient.getMenuById(id); 
        model.addAttribute("menuItem", item);
        model.addAttribute("user", user);

        return "menuDetails"; 
    }


}
