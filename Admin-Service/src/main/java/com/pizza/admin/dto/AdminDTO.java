package com.pizza.admin.dto;

import com.pizza.admin.entity.Admin;

import lombok.Data;

@Data
public class AdminDTO {
	 private Long id;
	    private String username;
	    private String email;
	    private String phoneNumber;
	    private Admin.Role adminRole;
	    private boolean active;
	    private Long branchId;
	    private String branchName;
	    private String token;
		private String branchAddress;
		 private String password;
}
