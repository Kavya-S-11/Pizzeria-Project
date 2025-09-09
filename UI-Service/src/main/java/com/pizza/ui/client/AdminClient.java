package com.pizza.ui.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.pizza.ui.dto.AdminDTO;

@FeignClient(name = "admin-service", url = "http://localhost:8087/api/admins", contextId="adminClient")
public interface AdminClient {

    // ================= LOGIN =================
	@PostMapping("/login")
    AdminDTO login(@RequestParam("username") String username,
                   @RequestParam("password") String password);

    // ================= GET ALL ADMINS =================
    @GetMapping
    List<AdminDTO> getAllAdmins(@RequestParam("token") String token);

    // ================= GET ADMIN BY ID =================
    @GetMapping("/{id}")
    AdminDTO getAdminById(@PathVariable("id") Long id,
    						@RequestParam("token") String token);

    // ================= ADD STORE ADMIN =================
    @PostMapping("/store-admin")
    AdminDTO addStoreAdmin(@RequestBody AdminDTO adminDTO,
                           @RequestParam(required = false) Long branchId,
                           @RequestHeader("Authorization") String token);

    // ================= UPDATE ADMIN =================
    @PutMapping("/{id}")
    AdminDTO updateAdmin(@PathVariable("id") Long id,
                         @RequestBody AdminDTO admin,
                         @RequestParam("token") String token);

    // ================= DEACTIVATE ADMIN =================
    @PutMapping("/{id}/deactivate")
    AdminDTO deactivateAdmin(@PathVariable("id") Long id,
    							@RequestParam("token") String token);

    // ================= ACTIVATE ADMIN =================
    @PutMapping("/{id}/activate")
    AdminDTO activateAdmin(@PathVariable("id") Long id,
    						@RequestParam("token") String token);

    // ================= DELETE ADMIN =================
    @DeleteMapping("/{id}")
    String deleteAdmin(@PathVariable("id") Long id,
    					@RequestParam("token") String token);

    // ================= GET ADMINS BY BRANCH =================
    @GetMapping("/branch/{branchId}")
    List<AdminDTO> getAdminsByBranch(@PathVariable("branchId") Long branchId,
    									@RequestParam("token") String token);

    // get admin info from token
    @GetMapping("/from-token")
    AdminDTO getAdminFromToken(@RequestParam("token") String token);
}
