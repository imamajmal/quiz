package com.example.Quiz.controller;

import com.example.Quiz.entity.EmailDetails;
import com.example.Quiz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // ✅ Send simple custom mail
    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    // ✅ Send quiz result mail
    @PostMapping("/quizResult")
    public String sendQuizResult(
            @RequestParam String recipient,
            @RequestParam String quizTitle,
            @RequestParam int score) {
        return emailService.sendQuizResultMail(recipient, quizTitle, score);
    }
}

