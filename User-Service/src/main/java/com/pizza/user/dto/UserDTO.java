package com.pizza.user.dto;

import com.pizza.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String phone;
    private String address;

    // Constructor to map entity + token
    public UserDTO(String token, User user) {
        this.token = token;
        this.id = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone=user.getPhone();
        this.address=user.getAddress();
    }
}
