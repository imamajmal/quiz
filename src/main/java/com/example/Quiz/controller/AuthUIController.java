package com.example.Quiz.controller;

import com.example.Quiz.entity.User;
import com.example.Quiz.service.UserUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthUIController {

    @Autowired
    private UserUIService userUIService;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userUIService.register(user);
        model.addAttribute("success", "Registered Successfully!");
        return "login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        try {
            User loggedInUser = userUIService.login(user.getUsername(), user.getPassword());
            if (loggedInUser.getRole().name().equals("ADMIN"))
                return "admin-dashboard";
            else
                return "participant-dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

