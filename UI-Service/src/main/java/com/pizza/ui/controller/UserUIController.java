package com.pizza.ui.controller;

import com.pizza.ui.client.UserClient;
import com.pizza.ui.dto.UserDTO;
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

    // ==================== USER: REGISTER ====================
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user, Model model) {
        UserDTO created = userClient.register(user);
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
        UserDTO request = new UserDTO();
        request.setEmail(email);
        request.setPassword(password);

        UserDTO loggedIn = userClient.login(request);

        if (loggedIn == null) {
            model.addAttribute("error", "Invalid credentials.");
            return "user-login";
        }

        session.setAttribute("jwtToken", loggedIn.getToken());
        session.setAttribute("user", loggedIn);
        return "redirect:/ui/users/dashboard"; // safer to redirect
    }
    
    //======================DASHBOARD============
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user"); 
        System.out.println("User in session: " + user);
        if (user == null) {
            return "redirect:/ui/users/login"; 
        }
        model.addAttribute("user", user); // now JSP can access user.name and user.email
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

        List<UserDTO> users = userClient.getAllUsers(token);
        model.addAttribute("users", users);
        return "users-list";
    }


    // ==================== ADMIN: ADD USER ====================
    @PostMapping("/admin/add")
    public String adminAddUser(@ModelAttribute UserDTO user, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        userClient.addUser(user, token);
        return "redirect:/ui/users/admin/list";
    }

    @GetMapping("/admin/add")
    public String showAddUserForm(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }
        model.addAttribute("user", new UserDTO());
        return "user-add"; // your JSP page for adding a user
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

        UserDTO user = userClient.getUserById(id, token);
        model.addAttribute("user", user);
        return "user-edit";
    }

    // ==================== ADMIN: UPDATE USER ====================
    @PostMapping("/admin/update/{id}")
    public String adminUpdateUser(@PathVariable Long id,
                                  @ModelAttribute("user") UserDTO user,
                                  HttpSession session,
                                  Model model) {
        String token = (String) session.getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        try {
            UserDTO updated = userClient.updateUser(id, user, token);
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
    @DeleteMapping("/admin/delete/{id}")
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
