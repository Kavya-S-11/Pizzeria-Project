package com.pizza.menu.controller;

import com.pizza.menu.entity.Menu;
import com.pizza.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Autowired
    private MenuService menuService;

    // Get all menu items
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllItems();
    }

    // Get menu by ID
    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getItemById(id);
    }

    // Add new menu item
    @PostMapping("/add")
    public Menu addMenu(@RequestBody Menu menuItem) {
        return menuService.addItem(menuItem);
    }

    // Edit menu item
    @PutMapping("/edit/{id}")
    public Menu editMenu(@PathVariable Long id, @RequestBody Menu menuItem) {
        return menuService.updateItem(id, menuItem);
    }

    // Delete menu item
    @DeleteMapping("/delete/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteItem(id);
    }
}
