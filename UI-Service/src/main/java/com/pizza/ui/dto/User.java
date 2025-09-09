package com.pizza.ui.dto;

import com.pizza.ui.dto.AdminDTO.Role;

import lombok.Data;

@Data
public class User {

	private Long id;
    private String email;
    private String password;
    private String name; 
}
