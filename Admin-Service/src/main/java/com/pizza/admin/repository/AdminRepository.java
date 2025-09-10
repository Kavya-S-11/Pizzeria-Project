package com.pizza.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.admin.entity.Admin;



public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByUsernameAndPassword(String username, String password);
	Optional<Admin> findByUsername(String username);
	List<Admin> findByBranchId(Long branchId);
}
