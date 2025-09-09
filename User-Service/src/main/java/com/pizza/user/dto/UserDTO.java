package com.pizza.user.dto;

import com.pizza.user.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private String token;
    private User user;

    public UserDTO(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
