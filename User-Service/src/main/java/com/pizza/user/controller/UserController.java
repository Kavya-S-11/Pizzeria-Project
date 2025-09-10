package com.pizza.user.controller;

import com.pizza.user.dto.UserDTO;
import com.pizza.user.entity.User;
import com.pizza.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ==================== REGISTER ====================
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.registerUser(user);
    }

    // ==================== LOGIN ====================
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO user) {
        return service.login(user.getEmail(), user.getPassword());
    }


    // ==================== GET ALL USERS ====================
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // ==================== GET USER BY ID ====================
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // ==================== DELETE USER ====================
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted with ID: " + id;
    }

    // ==================== UPDATE USER ====================
    @PostMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user) {
        return service.patchUser(id, user);
    }
    

}

