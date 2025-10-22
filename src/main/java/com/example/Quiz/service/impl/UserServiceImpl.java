package com.example.Quiz.service.impl;

import com.example.Quiz.entity.User;
import com.example.Quiz.repository.UserRepository;
import com.example.Quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Register User
    @Override
    public User registerUser(User user) {
        // Check if username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }

    // ✅ Login User (without JWT)
    @Override
    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return "Invalid username!";
        }

        User user = userOpt.get();

        // Match the raw password with encoded one
        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful! Welcome " + user.getRole();
        } else {
            return "Invalid password!";
        }
    }
}


