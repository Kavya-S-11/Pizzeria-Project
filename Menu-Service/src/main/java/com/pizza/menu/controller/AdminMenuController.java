package com.pizza.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizza.menu.entity.Menu;
import com.pizza.menu.service.MenuService;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Autowired
    private MenuService menuService;

    // get all menu items
    @GetMapping
    public String viewMenu(Model model) {
        model.addAttribute("menuList", menuService.getAllItems());
        return "adminMenu"; 
    }

    // Show add form
    @GetMapping("/add")
    public String addMenuForm(Model model) {
        model.addAttribute("menuItem", new Menu());
        return "adminAddMenu";
    }

    // Handle add
    @PostMapping("/add")
    public String addMenuSubmit(@ModelAttribute Menu menuItem) {
        menuService.addItem(menuItem);
        return "redirect:/admin/menu";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editMenuForm(@PathVariable Long id, Model model) {
        Menu item = menuService.getItemById(id);
        model.addAttribute("menuItem", item);
        return "adminEditMenu"; // adminEditMenu.jsp
    }

    // Handle edit
    @PostMapping("/edit")
    public String editMenuSubmit(@ModelAttribute Menu menuItem) {
        menuService.updateItem(menuItem.getId(), menuItem);
        return "redirect:/admin/menu";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.deleteItem(id);
        return "redirect:/admin/menu";
    }
}
