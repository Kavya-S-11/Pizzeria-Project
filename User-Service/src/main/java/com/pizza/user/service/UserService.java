package com.pizza.user.service;

import com.pizza.user.config.JwtUtil;
import com.pizza.user.dto.UserDTO;
import com.pizza.user.entity.User;
import com.pizza.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(User user) {
        return repo.save(user);
    }

    public UserDTO login(String email, String password) {
        User user = repo.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);

        if (user == null) {
            return null;
        }

        String token = jwtUtil.generateToken(user);
        return new UserDTO(token,user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    public User patchUser(Long id, User userDetails) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (userDetails.getName() != null) {
            existing.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null) {
            existing.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPassword() != null) {
            existing.setPassword(userDetails.getPassword());
        }
        if (userDetails.getPhone() != null) {
            existing.setPhone(userDetails.getPhone());
        }
        if (userDetails.getAddress() != null) {
            existing.setAddress(userDetails.getAddress());
        }

        return repo.save(existing);
    }
}
