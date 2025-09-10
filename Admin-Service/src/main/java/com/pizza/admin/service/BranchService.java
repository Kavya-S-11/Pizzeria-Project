package com.pizza.admin.service;


import org.springframework.stereotype.Service;

import com.pizza.admin.entity.Branch;
import com.pizza.admin.repository.BranchRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BranchService{

	@Autowired
    private BranchRepository branchRepo;


    public List<Branch> getAllBranches() {
        return branchRepo.findAll();
    }


    public Branch addBranch(Branch branch) {
        return branchRepo.save(branch);
    }
	

	public void deleteBranch(Long id) {
	    if (!branchRepo.existsById(id)) {
	        throw new RuntimeException("Branch not found");
	    }
	    branchRepo.deleteById(id);
	}



	public Branch getBranchById(Long id) {
	    return branchRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Branch not found"));
	}


	public Branch updateBranch(Branch branch) {
	    return branchRepo.save(branch);
	}
	


}
