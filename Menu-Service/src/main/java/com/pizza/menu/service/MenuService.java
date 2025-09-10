package com.pizza.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pizza.menu.entity.Menu;
import com.pizza.menu.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
   

    public List<Menu> getAllItems() {
        return menuRepository.findAll();
    }

    public Menu getItemById(Long id) {
        Optional<Menu> item = menuRepository.findById(id);
        return item.orElse(null); // or throw exception if preferred
    }

    public Menu addItem(Menu menuItem) {
        return menuRepository.save(menuItem);
    }

    public Menu updateItem(Long id, Menu menu) {
        return menuRepository.findById(id).map(existing -> {
            if (menu.getName() != null) existing.setName(menu.getName());
            if (menu.getDescription() != null) existing.setDescription(menu.getDescription());
            if (menu.getPrice() != 0) existing.setPrice(menu.getPrice());
            if (menu.getCategory() != null) existing.setCategory(menu.getCategory());
            if (menu.getImageUrl() != null) existing.setImageUrl(menu.getImageUrl());
            return menuRepository.save(existing);
        }).orElse(null);
    }


    public void deleteItem(Long id) {
        menuRepository.deleteById(id);
    }
}
