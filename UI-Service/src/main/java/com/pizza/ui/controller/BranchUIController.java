package com.pizza.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pizza.ui.client.AdminClient;
import com.pizza.ui.client.BranchClient;
import com.pizza.ui.dto.AdminDTO;
import com.pizza.ui.dto.BranchDTO;

@Controller
@RequestMapping("/ui/admins/branches")
public class BranchUIController {

    @Autowired
    private BranchClient branchClient;
    
    @Autowired
    private AdminClient adminClient;

    // ================= MANAGE BRANCHES PAGE =================
    @GetMapping("/manage")
    public String showManageBranches(@RequestParam String token, Model model) {
        AdminDTO admin = adminClient.getAdminFromToken(token);
        if (admin == null) return "redirect:/ui/admin/login";

        model.addAttribute("admin", admin);
        model.addAttribute("jwtToken", token);
        model.addAttribute("token", token);
        model.addAttribute("branches", branchClient.getAllBranches(token));
        model.addAttribute("branchToEdit", new com.pizza.ui.dto.BranchDTO());
        return "manage-branches";
    }

    // ================= ADD BRANCH =================
    @PostMapping("/add")
    public String addBranch(@RequestParam String token,
                            @ModelAttribute BranchDTO branchDTO) {
        branchClient.addBranch(branchDTO, "Bearer " + token); // include Bearer
        return "redirect:/ui/admins/branches/manage?token=" + token;
    }


    // ================= EDIT BRANCH =================
    @GetMapping("/edit/{id}")
    public String editBranch(@RequestParam String token,
                             @PathVariable Long id, Model model) {
        BranchDTO branch = branchClient.getBranchById(id, token); // GET /branches/{id} in Admin-Service
        model.addAttribute("branchToEdit", branch);
        model.addAttribute("branches", branchClient.getAllBranches(token));
        model.addAttribute("token", token);
        return "manage-branches";
    }

    // ================= UPDATE BRANCH =================
    @PostMapping("/update")
    public String updateBranch(@RequestParam String token,
                               @ModelAttribute BranchDTO branchDTO) {
    	 branchClient.updateBranch(branchDTO.getId(), branchDTO, "Bearer " + token);
        return "redirect:/ui/admins/branches/manage?token=" + token;
    }

    // ================= DELETE BRANCH =================
    @GetMapping("/delete/{id}")
    public String deleteBranch(@RequestParam String token,
                               @PathVariable Long id) {
        branchClient.deleteBranch(id, token); // DELETE /branches/{id} in Admin-Service
        return "redirect:/ui/admins/branches/manage?token=" + token;
    }
}
