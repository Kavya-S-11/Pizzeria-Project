package com.pizza.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizza.menu.service.MenuService;



@Controller
@RequestMapping("/menu")
public class UserMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String viewMenu(Model model) {
        model.addAttribute("menuList", menuService.getAllItems());
        return "menu"; 
    }
}
