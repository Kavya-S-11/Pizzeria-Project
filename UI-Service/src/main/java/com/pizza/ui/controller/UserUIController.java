package com.pizza.ui.controller;

import com.pizza.ui.client.UserClient;
import com.pizza.ui.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ui/users")
public class UserUIController {

    private final UserClient userClient;

    public UserUIController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Controller works!";
    }

    // ==================== USER: REGISTER ====================
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "user-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        User created = userClient.register(user);
        if (created != null) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "user-login";
        } else {
            model.addAttribute("error", "Registration failed.");
            return "user-register";
        }
    }

    // ==================== USER: LOGIN ====================
    @GetMapping("/login")
    public String showLoginPage() {
        return "user-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User loggedIn = userClient.login(email, password);
        if (loggedIn == null) {
            model.addAttribute("error", "Invalid credentials.");
            return "user-login";
        }

        // store token from loggedIn (assuming your User DTO contains token)
        session.setAttribute("jwtToken", loggedIn.getToken());
        session.setAttribute("user", loggedIn);

        return "user-dashboard";
    }

    // ==================== USER: LOGOUT ====================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/ui/users/login";
    }

    // ==================== ADMIN: LIST USERS ====================
    @GetMapping("/admin/list")
    public String adminListUsers(HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login"; 
        }

        List<User> users = userClient.getAllUsers(token);
        model.addAttribute("users", users);
        return "admin/users-list";
    }

    // ==================== ADMIN: ADD USER ====================
    @GetMapping("/admin/add")
    public String showAdminAddUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/admin/add")
    public String adminAddUser(@ModelAttribute User user, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        userClient.addUser(user, token);
        return "redirect:/ui/users/admin/list";
    }

    // ==================== ADMIN: EDIT USER PAGE ====================
    @GetMapping("/admin/edit/{id}")
    public String showAdminEditUserPage(@PathVariable Long id,
                                        HttpSession session,
                                        Model model) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        User user = userClient.getUserById(id, token);
        model.addAttribute("user", user);
        return "user-edit"; 
    }

    // ==================== ADMIN: UPDATE USER ====================
    @PostMapping("/admin/update/{id}")
    public String adminUpdateUser(@PathVariable Long id,
                                  @ModelAttribute("user") User user,
                                  HttpSession session,
                                  Model model) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        try {
            User updated = userClient.updateUser(id, user, token);
            if (updated != null) {
                return "redirect:/ui/users/admin/list";
            } else {
                model.addAttribute("error", "Failed to update user.");
                return "user-edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error updating user: " + e.getMessage());
            return "user-edit";
        }
    }

    // ==================== ADMIN: DELETE USER ====================
    @GetMapping("/admin/delete/{id}")
    public String adminDeleteUser(@PathVariable Long id,
                                  HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        userClient.deleteUser(id, token);
        return "redirect:/ui/users/admin/list";
    }
}
