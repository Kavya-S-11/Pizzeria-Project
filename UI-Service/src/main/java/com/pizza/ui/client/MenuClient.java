package com.pizza.ui.client;

import com.pizza.ui.dto.MenuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "menu-service", url = "http://localhost:8082")
public interface MenuClient {


    @GetMapping("/admin/menu")
    List<MenuDTO> getAllMenus();

    @GetMapping("/admin/menu/{id}")
    MenuDTO getMenuById(@PathVariable("id") Long id);

    @PostMapping("/admin/menu/add")
    MenuDTO addMenu(@RequestBody MenuDTO menuItem);

    @PutMapping("/admin/menu/edit/{id}")
    MenuDTO editMenu(@PathVariable("id") Long id, @RequestBody MenuDTO menuItem);

    @DeleteMapping("/admin/menu/delete/{id}")
    void deleteMenu(@PathVariable("id") Long id);
    
    @GetMapping("/menu")
    List<MenuDTO> getAllMenusforUser();

    @GetMapping("/menu/{id}")
    MenuDTO getMenuByIdforUser(@PathVariable("id") Long id);
}
