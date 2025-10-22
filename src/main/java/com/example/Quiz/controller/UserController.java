package com.example.Quiz.controller;

import com.example.Quiz.entity.User;
import com.example.Quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Register new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return "User registered successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // ✅ Login user (without JWT)
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        String response = userService.loginUser(user.getUsername(), user.getPassword());
        return response;
    }

}


