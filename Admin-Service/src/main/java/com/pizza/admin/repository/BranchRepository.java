package com.pizza.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.admin.entity.Branch;



public interface BranchRepository extends JpaRepository<Branch, Long> {

}
