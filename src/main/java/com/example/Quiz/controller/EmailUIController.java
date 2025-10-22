package com.example.Quiz.controller;

import com.example.Quiz.entity.EmailDetails;
import com.example.Quiz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/email")
public class EmailUIController {

    @Autowired
    private EmailService emailService;

    // 🟢 Load Email Form
    @GetMapping("/form")
    public String showEmailForm(Model model) {
        model.addAttribute("email", new EmailDetails());
        return "email/send-email"; // loads send-email.html
    }

    // 🟢 Send Email (POST)
    @PostMapping("/send")
    public String sendEmail(@ModelAttribute("email") EmailDetails details, Model model) {
        String response = emailService.sendSimpleMail(details);

        if (response.contains("success")) {
            model.addAttribute("successMessage", response);
        } else {
            model.addAttribute("errorMessage", response);
        }

        model.addAttribute("email", new EmailDetails()); // reset form
        return "email/send-email";
    }
}

