package com.pizza.ui.client;

import com.pizza.ui.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8088/api/users")
public interface UserClient {

    // ==================== REGISTER ====================
    @PostMapping("/register")
    UserDTO register(@RequestBody UserDTO user);

    // ==================== LOGIN ====================
    @PostMapping("/login")
    UserDTO login(@RequestParam String email,
                  @RequestParam String password);

    // ==================== ADMIN: GET ALL USERS ====================
    @GetMapping
    List<UserDTO> getAllUsers(@RequestHeader("Authorization") String token);

    // ==================== ADMIN: GET USER BY ID ====================
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable("id") Long id,
                        @RequestHeader("Authorization") String token);

    // ==================== ADMIN: ADD USER ====================
    @PostMapping
    UserDTO addUser(@RequestBody UserDTO user,
                    @RequestHeader("Authorization") String token);

    // ==================== ADMIN: UPDATE USER ====================
    @PatchMapping("/{id}")
    UserDTO updateUser(@PathVariable("id") Long id,
                       @RequestBody UserDTO user,
                       @RequestHeader("Authorization") String token);

    // ==================== ADMIN: DELETE USER ====================
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id,
                    @RequestHeader("Authorization") String token);
}
