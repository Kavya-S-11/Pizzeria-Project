package com.pizza.user.controller;

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

    // Register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.registerUser(user);
    }

    // Login
    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted with ID: " + id;
    }
    
 // Update User
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user) {
        return service.patchUser(id, user);
    }

}
