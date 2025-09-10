
package com.pizza.admin.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pizza.admin.config.JwtUtil;
import com.pizza.admin.dto.AdminDTO;
import com.pizza.admin.entity.Admin;
import com.pizza.admin.entity.Branch;
import com.pizza.admin.entity.Admin.Role;
import com.pizza.admin.service.AdminService;
import com.pizza.admin.service.BranchService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BranchService branchService;
    
    @Autowired
    private JwtUtil jwtUtil;


    // ================= LOGIN =================

    @PostMapping("/login")
    public AdminDTO login(@RequestParam String username,
                          @RequestParam String password) {

        Admin admin = adminService.login(username, password)
                      .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtUtil.generateToken(admin);  // <-- generate JWT
        AdminDTO dto = adminService.toDTO(admin);
        dto.setToken(token);                            // <-- set token
        return dto;
    }


    // ================= GET ALL ADMINS =================
    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins().stream()
                .map(adminService::toDTO)
                .collect(Collectors.toList());
    }

    // ================= GET ADMIN BY ID =================
    @GetMapping("/{id}")
    public AdminDTO getAdminById(@PathVariable Long id, @RequestParam String token) {
        return adminService.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }


    // ================= ADD NEW ADMIN =================
    @PostMapping("/store-admin")
    public AdminDTO addNewAdmin(@RequestBody Admin admin,
                                @RequestParam(required = false) Long branchId) {
        if (branchId != null) {
            Branch branch = branchService.getBranchById(branchId);
            admin.setBranch(branch);
        }
        return adminService.toDTO(adminService.addNewAdmin(admin));
    }

    // ================= UPDATE ADMIN =================
    @PutMapping("/{id}")
    public AdminDTO updateAdmin(@PathVariable Long id,
                                @RequestBody Admin admin,
                                @RequestParam(required = false) Long branchId) {
        admin.setId(id);
        if (branchId != null) {
            Branch branch = branchService.getBranchById(branchId);
            admin.setBranch(branch);
        }
        return adminService.toDTO(adminService.updateAdmin(admin));
    }

    // ================= DEACTIVATE / ACTIVATE =================
    @PutMapping("/{id}/deactivate")
    public AdminDTO deactivateAdmin(@PathVariable Long id) {
        adminService.deactivateAdmin(id);
        return adminService.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @PutMapping("/{id}/activate")
    public AdminDTO activateAdmin(@PathVariable Long id) {
        adminService.activateAdmin(id);
        return adminService.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }


    // ================= DELETE ADMIN =================
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin deleted successfully!";
    }

    // ================= GET ADMINS BY BRANCH =================
    @GetMapping("/branch/{branchId}")
    public List<AdminDTO> getAdminsByBranch(@PathVariable Long branchId) {
        return adminService.getAdminsByBranch(branchId);
    }


    //================DASHBOARD==================
    @GetMapping("/dashboard/{adminId}")
    public AdminDTO getDashboard(@PathVariable Long adminId) {
        return adminService.getAdminDTOById(adminId);
    }

    
    @GetMapping("/from-token")
    public AdminDTO getAdminFromToken(@RequestParam("token") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return adminService.getAdminFromToken(token); // make sure it returns DTO, not Optional
    }


    @GetMapping("/profile")
    public AdminDTO getProfile(@RequestParam String token) {
        return adminService.getAdminFromToken(token);
    }

}


