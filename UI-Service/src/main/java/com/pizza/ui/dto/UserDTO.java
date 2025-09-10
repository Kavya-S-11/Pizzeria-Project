package com.pizza.ui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDTO {
	
	@JsonProperty("userId")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String token;  
    private String phone;
    private String address;
    
}
