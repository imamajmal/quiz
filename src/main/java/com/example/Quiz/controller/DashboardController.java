package com.example.Quiz.controller;

import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizAttemptRepository attemptRepository;

    // 🟢 Landing page
    @GetMapping("/")
    public String homePage(Model model) {
        long totalQuizzes = quizRepository.count();
        long totalAttempts = attemptRepository.count();

        model.addAttribute("totalQuizzes", totalQuizzes);
        model.addAttribute("totalAttempts", totalAttempts);

        return "dashboard"; // loads dashboard.html
    }
}

