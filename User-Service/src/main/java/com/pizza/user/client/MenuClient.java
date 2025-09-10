package com.pizza.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pizza.user.dto.MenuDTO;

@FeignClient(name = "menu-service", url = "http://localhost:8081")
public interface MenuClient {

    @GetMapping("/menu/{id}")
    MenuDTO getMenuItemById(@PathVariable("id") Long id);
}
