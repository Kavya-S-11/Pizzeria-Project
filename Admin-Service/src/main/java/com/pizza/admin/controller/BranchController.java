package com.pizza.admin.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.admin.entity.Branch;
import com.pizza.admin.service.AdminService;
import com.pizza.admin.service.BranchService;

@RestController
@RequestMapping("/api/admins/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private AdminService adminService;

    // GET all branches (for UI-Service)
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    // GET branch by ID
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    // POST new branch
    @PostMapping
    public Branch addBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }

    // PUT update branch
    @PutMapping("/{id}")
    public Branch updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        branch.setId(id);
        return branchService.updateBranch(branch);
    }

    // DELETE branch
    @DeleteMapping("/{id}")
    public String deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return "Branch deleted successfully!";
    }

    // GET admins count for each branch (optional, used for UI table)
    @GetMapping("/admins-count")
    public Map<Long, Boolean> getBranchAdminMap() {
        List<Branch> branches = branchService.getAllBranches();
        return branches.stream()
                .collect(Collectors.toMap(
                        Branch::getId,
                        b -> !adminService.getAdminsByBranch(b.getId()).isEmpty()
                ));
    }
}

