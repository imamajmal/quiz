package com.example.Quiz.controller;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.QuizAttempt;
import com.example.Quiz.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    // Browse all available quizzes
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return participantService.getAllQuizzes();
    }

    // View a quiz with all questions
    @GetMapping("/quiz/{quizId}")
    public Quiz getQuizById(@PathVariable Long quizId) {
        return participantService.getQuizById(quizId);
    }

    // Start a quiz attempt
    @PostMapping("/start/{quizId}")
    public QuizAttempt startQuiz(@PathVariable Long quizId, @RequestParam String participantName) {
        return participantService.startQuiz(participantName, quizId);
    }

    // Submit quiz
    @PostMapping("/submit/{attemptId}")
    public QuizAttempt submitQuiz(@PathVariable Long attemptId, @RequestBody List<String> answers) {
        return participantService.submitQuiz(attemptId, answers);
    }
}

