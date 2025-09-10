package com.pizza.ui.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pizza.ui.dto.AdminDTO;
import com.pizza.ui.dto.BranchDTO;
import com.pizza.ui.client.AdminClient;
import com.pizza.ui.client.BranchClient;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/ui/admins")
public class AdminUIController {

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private BranchClient branchClient;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUIController.class);

    @GetMapping("/login")
    public String showLoginPage() {
    	log.info(">>> Entered /ui/admin/login GET mapping in UI-Service");
        return "admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        System.out.println("Login attempt: " + username + " / " + password);
        try {
            AdminDTO admin = adminClient.login(username, password);
            System.out.println("Admin returned: " + admin);

            model.addAttribute("admin", admin);
            model.addAttribute("jwtToken", admin.getToken());

            if (admin.getAdminRole() == AdminDTO.Role.SUPER_ADMIN) {
                // fetch additional data for dashboard if needed
                return "super-admin-dashboard";
            } else {
                return "store-admin-dashboard";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid credentials");
            return "admin-login";
        }
    }


    @GetMapping("/dashboard")
    public String showDashboard(HttpServletRequest request, Model model) {
        String sessionToken = (String) request.getSession().getAttribute("jwtToken");
        if (sessionToken == null) {
            return "redirect:/ui/admins/login";
        }

        try {
            AdminDTO admin = adminClient.getAdminFromToken(sessionToken); // use sessionToken
            if (admin == null) {
                request.getSession().invalidate();
                return "redirect:/ui/admins/login";
            }

            
            model.addAttribute("admin", admin);
            model.addAttribute("jwtToken", sessionToken);
            
            if (admin.getAdminRole() == AdminDTO.Role.SUPER_ADMIN) {
                // get all admins
                List<AdminDTO> allAdmins = adminClient.getAllAdmins(sessionToken);

                // filter out SUPER_ADMIN
                List<AdminDTO> nonSuperAdmins = allAdmins.stream()
                    .filter(a -> a.getAdminRole() != AdminDTO.Role.SUPER_ADMIN)
                    .toList();

                model.addAttribute("allAdmins", nonSuperAdmins);
                model.addAttribute("branches", branchClient.getAllBranches(sessionToken));
                return "super-admin-dashboard";
            } else {
                return "store-admin-dashboard";
            }
        } catch (Exception e) {
            request.getSession().invalidate();
            return "redirect:/ui/admins/login";
        }
    }



    @GetMapping("/logout")
    public String logout(@RequestParam(required = false) String token, HttpServletRequest request) {
        
        request.getSession().invalidate();  
        return "redirect:/ui/admins/login";
    }


    @PostMapping("/store-admin")
    public String addStoreAdmin(@ModelAttribute AdminDTO adminDTO, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("jwtToken");
        if (token == null) {
            return "redirect:/ui/admins/login";
        }

        adminClient.addStoreAdmin(adminDTO, adminDTO.getBranchId(), "Bearer " + token);
        return "redirect:/ui/admins/dashboard"; // no token needed in URL
    }


    @PostMapping("/admin/{id}/deactivate")
    public String deactivateAdmin(@RequestParam String token, @PathVariable Long id) {
        adminClient.deactivateAdmin(id, token);
        return "redirect:/ui/admins/dashboard?token=" + token;
    }

    @PostMapping("/admin/{id}/activate")
    public String activateAdmin(@RequestParam String token, @PathVariable Long id) {
        adminClient.activateAdmin(id, token);
        return "redirect:/ui/admins/dashboard?token=" + token;
    }

    @PostMapping("/admin/{id}/delete")
    public String deleteAdmin(@RequestParam String token, @PathVariable Long id) {
        adminClient.deleteAdmin(id, token);
        return "redirect:/ui/admins/dashboard?token=" + token;
    }

 // ===================== ADD STORE ADMIN PAGE =====================
    @GetMapping("/add-store-admin-page")
    public String showAddStoreAdminPage(@RequestParam String token,
                                        HttpServletRequest request,
                                        Model model) {
        // clean token
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // fetch admin info
        AdminDTO superAdmin = adminClient.getAdminFromToken(token);
        if (superAdmin == null || !AdminDTO.Role.SUPER_ADMIN.equals(superAdmin.getAdminRole())) {
            return "redirect:/ui/admins/login";
        }

        // store token in session and model
        request.getSession().setAttribute("jwtToken", token);
        model.addAttribute("jwtToken", token);
        model.addAttribute("admin", superAdmin);
        model.addAttribute("newAdmin", new AdminDTO());
        model.addAttribute("branches", branchClient.getAllBranches(token)); // pass token here

        return "add-store-admin";
    }



 // ===================== PROFILE PAGE =====================

    @GetMapping("/profile")
    public String showProfile(@RequestParam String token,
                              HttpServletRequest request,
                              Model model) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        request.getSession().setAttribute("jwtToken", token);

        AdminDTO admin = adminClient.getAdminFromToken(token);
        if (admin == null) return "redirect:/ui/admins/login";

        model.addAttribute("admin", admin);
        model.addAttribute("jwtToken", token);
        model.addAttribute("roleName", admin.getAdminRole().name());

        try {
            model.addAttribute("branches", branchClient.getAllBranches(token));
        } catch (Exception ex) {
            log.error("Failed to fetch branches for profile view", ex);
            model.addAttribute("branches", List.of());
        }

        return "admin-profile";
    }

    @GetMapping("/update/{id}")
    public String showUpdateAdminPage(@PathVariable Long id,
                                      @RequestParam String token,
                                      HttpServletRequest request,
                                      Model model) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        request.getSession().setAttribute("jwtToken", token);

        AdminDTO admin = adminClient.getAdminById(id, token);
        if (admin == null) {
            return "redirect:/ui/admins/dashboard";
        }

        model.addAttribute("admin", admin);
        model.addAttribute("jwtToken", token);
        model.addAttribute("roleName", admin.getAdminRole().name());
        log.info("Loaded profile for admin id={}, role={}", admin.getId(), admin.getAdminRole());

        try {
            model.addAttribute("branches", branchClient.getAllBranches(token));
        } catch (Exception ex) {
            log.error("Failed to fetch branches for update page", ex);
            model.addAttribute("branches", List.of());
        }

        return "admin-profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute AdminDTO adminDTO,
                                HttpServletRequest request,
                                Model model) {
        try {
            String token = (String) request.getSession().getAttribute("jwtToken");
            if (token == null) return "redirect:/ui/admins/login";

            AdminDTO loggedInAdmin = adminClient.getAdminFromToken(token);
            if (loggedInAdmin == null) return "redirect:/ui/admins/login";

            Long targetAdminId = (loggedInAdmin.getAdminRole() == AdminDTO.Role.SUPER_ADMIN)
                    ? adminDTO.getId()
                    : loggedInAdmin.getId();

            if (targetAdminId == null) {
                model.addAttribute("admin", adminDTO);
                model.addAttribute("error", "Invalid admin ID. Cannot update profile.");
                return "admin-profile";
            }

            // Preserve fields
            AdminDTO existing = adminClient.getAdminById(targetAdminId, token);
            adminDTO.setId(targetAdminId);
            adminDTO.setActive(existing.isActive());
            adminDTO.setPassword(existing.getPassword());

            // Call backend to update
            AdminDTO updatedAdmin = adminClient.updateAdmin(targetAdminId, adminDTO, token);

            model.addAttribute("admin", updatedAdmin);
            model.addAttribute("success", "Profile updated successfully!");
            return "admin-profile";

        } catch (Exception e) {
            log.error("Error updating profile", e);
            model.addAttribute("admin", adminDTO);
            model.addAttribute("error", "Failed to update profile. Please try again.");
            return "admin-profile";
        }
    }

}
