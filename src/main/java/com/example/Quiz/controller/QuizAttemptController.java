package com.example.Quiz.controller;

import com.example.Quiz.entity.QuizAttempt;
import com.example.Quiz.service.QuizAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    // ✅ Start a quiz attempt
    @PostMapping("/start/{quizId}")
    public QuizAttempt startQuiz(@PathVariable Long quizId, @RequestParam String participantName) {
        return quizAttemptService.startQuizAttempt(quizId, participantName);
    }

    // ✅ Submit answers and calculate score
    @PostMapping("/submit/{attemptId}")
    public QuizAttempt submitQuiz(@PathVariable Long attemptId, @RequestBody Map<Long, String> answers) {
        return quizAttemptService.submitQuizAttempt(attemptId, answers);
    }

    // ✅ Get attempts by participant
    @GetMapping("/user/{participantName}")
    public List<QuizAttempt> getAttempts(@PathVariable String participantName) {
        return quizAttemptService.getAttemptsByParticipant(participantName);
    }
}

