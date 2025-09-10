package com.pizza.ui.dto;

import lombok.Data;

@Data
public class BranchDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
}
