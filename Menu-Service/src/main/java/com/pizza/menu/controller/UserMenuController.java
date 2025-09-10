package com.pizza.menu.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pizza.menu.dto.MenuDTO;
import com.pizza.menu.entity.Menu;
import com.pizza.menu.service.MenuService;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/menu")
public class UserMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuItemById(@PathVariable Long id) {
        Menu item = menuService.getItemById(id);
        if (item == null) return ResponseEntity.notFound().build();
        MenuDTO dto = convertToDTO(item);
        return ResponseEntity.ok(dto);
    }

    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setCategory(menu.getCategory());
        dto.setImageUrl(menu.getImageUrl());
        return dto;
    }
}
