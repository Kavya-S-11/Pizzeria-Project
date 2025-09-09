package com.pizza.admin.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pizza.admin.entity.Admin;
import com.pizza.admin.repository.AdminRepository;



@Configuration
public class AdminInitializer {

	@Bean
	CommandLineRunner initAdmin(AdminRepository repo) {
	    return args -> {
	        if (repo.count() == 0) {
	            Admin superAdmin = new Admin();
	            superAdmin.setUsername("superadmin");
	            superAdmin.setPassword("admin123"); // encrypt later
	            superAdmin.setEmail("superadmin@pizzastore.com");
	            superAdmin.setAdminRole(Admin.Role.SUPER_ADMIN);
	            superAdmin.setBranch(null); // Super Admin has no branch
	            repo.save(superAdmin);
	        }
	    };
	}

}