package com.pizza.ui.client;

import com.pizza.ui.dto.MenuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "menu-service", url = "${menu-service.url}")
public interface MenuClient {

    @GetMapping
    List<MenuDTO> getAllMenus();
}
