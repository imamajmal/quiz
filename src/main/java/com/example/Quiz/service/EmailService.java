package com.example.Quiz.service;

import com.example.Quiz.entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendQuizResultMail(String recipient, String quizTitle, int score);
}

