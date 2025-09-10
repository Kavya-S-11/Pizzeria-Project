package com.pizza.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Menu addItem(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateItem(Long id, Menu menu) {
        Optional<Menu> existing = menuRepository.findById(id);
        if (existing.isPresent()) {
            Menu m = existing.get();
            m.setName(menu.getName());
            m.setDescription(menu.getDescription());
            m.setPrice(menu.getPrice());
            m.setCategory(menu.getCategory());
            return menuRepository.save(m);
        }
        return null; // or throw exception
    }

    public void deleteItem(Long id) {
        menuRepository.deleteById(id);
    }
}
