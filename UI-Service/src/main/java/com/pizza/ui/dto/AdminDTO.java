package com.pizza.ui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Role adminRole;
    private boolean active;
    private Long branchId;
    private String branchName;
    private String branchAddress;
    private String token;
    private String password;

    public enum Role {
    	@JsonProperty("SUPER_ADMIN")
        SUPER_ADMIN,
        
        @JsonProperty("ADMIN")
        ADMIN
    }
}
