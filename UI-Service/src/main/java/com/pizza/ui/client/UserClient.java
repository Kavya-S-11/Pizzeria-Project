package com.pizza.ui.client;

import com.pizza.ui.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8088/api/users")
public interface UserClient {

    // ==================== REGISTER (Normal user) ====================
    @PostMapping("/register")
    User register(@RequestBody User user);

    // ==================== LOGIN (Normal user) ====================
    @PostMapping("/login")
    User login(@RequestParam String email,
               @RequestParam String password);

    // ==================== ADMIN: GET ALL USERS ====================
    @GetMapping
    List<User> getAllUsers(@RequestParam("token") String token);

    // ==================== ADMIN: GET USER BY ID ====================
    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id,
                     @RequestParam("token") String token);

    // ==================== ADMIN: ADD USER ====================
    @PostMapping
    User addUser(@RequestBody User user,
                 @RequestParam("token") String token);

    // ==================== ADMIN: UPDATE USER ====================
    @PatchMapping("/{id}")
    User updateUser(@PathVariable("id") Long id,
                    @RequestBody User user,
                    @RequestParam("token") String token);

    // ==================== ADMIN: DELETE USER ====================
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id,
                    @RequestParam("token") String token);
}
