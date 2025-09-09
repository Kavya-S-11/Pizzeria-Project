package com.pizza.ui.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.pizza.ui.dto.BranchDTO;

@FeignClient(name = "admin-service", url = "http://localhost:8087/api/admins/branches", contextId="branchClient")
public interface BranchClient {

    // Get all branches
    @GetMapping
    List<BranchDTO> getAllBranches(@RequestHeader("Authorization") String token);

    // Get branch by ID
    @GetMapping("/{id}")
    BranchDTO getBranchById(@PathVariable("id") Long id,
                             @RequestHeader("Authorization") String token);

    // Add new branch
    @PostMapping
    BranchDTO addBranch(@RequestBody BranchDTO branch,
                        @RequestHeader("Authorization") String token);

    // Update branch
    @PutMapping("/{id}")
    BranchDTO updateBranch(@PathVariable("id") Long id,
                           @RequestBody BranchDTO branch,
                           @RequestHeader("Authorization") String token);


    // Delete branch
    @DeleteMapping("/{id}")
    void deleteBranch(@PathVariable("id") Long id,
                      @RequestHeader("Authorization") String token);
}
