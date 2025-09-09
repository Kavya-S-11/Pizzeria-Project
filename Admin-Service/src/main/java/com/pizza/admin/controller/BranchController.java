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




//package com.pizza.admin.controller;
//
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import com.pizza.admin.entiry.Branch;
//import com.pizza.admin.service.AdminService;
//import com.pizza.admin.service.BranchService;
//
//@Controller
//@RequestMapping("/admin/branches") 
//public class BranchController {
//
//    @Autowired
//    private BranchService branchService;
//
//    @Autowired
//    private AdminService adminService;
//    
//    @GetMapping("/manage-branches")
//    public String showManageBranches(@RequestParam(value = "editId", required = false) Long editId, Model model) {
//    	 List<Branch> branches = branchService.getAllBranches();
//    	   
//
//    	 Map<Long, Boolean> branchAdminMap = branches.stream()
//    		        .collect(Collectors.toMap(
//    		            Branch::getId,
//    		            b -> !adminService.getAdminsByBranch(b.getId()).isEmpty()
//    		        ));
//    	    
//    	    model.addAttribute("branches", branches);
//    	    model.addAttribute("branchAdminMap", branchAdminMap);
//        
//        if (editId != null) {
//            Branch branchToEdit = branchService.getBranchById(editId);
//            model.addAttribute("branchToEdit", branchToEdit);
//        } else {
//            model.addAttribute("branchToEdit", new Branch());
//        }
//        
//        return "manage-branches";
//    }
//
//    // Add or update branch
//    @PostMapping("/add-or-edit")
//    public String addOrEditBranch(@ModelAttribute Branch branch, Model model) {
//        if (branch.getId() == null) {
//            branchService.addBranch(branch);
//        } else {
//            branchService.updateBranch(branch);
//        }
//        model.addAttribute("branches", branchService.getAllBranches());
//        model.addAttribute("branchToEdit", new Branch()); // reset form
//        return "manage-branches";
//    }
//
//    // Delete branch
//    @GetMapping("/delete-branch/{id}")
//    public String deleteBranch(@PathVariable Long id, Model model) {
//        branchService.deleteBranch(id);
//        model.addAttribute("branches", branchService.getAllBranches());
//        model.addAttribute("branch", new Branch());
//        return "manage-branches";
//    }
//}
