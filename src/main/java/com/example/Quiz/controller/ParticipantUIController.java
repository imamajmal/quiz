package com.example.Quiz.controller;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.QuizAttempt;
import com.example.Quiz.service.ParticipantUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/participant")
public class ParticipantUIController {

    @Autowired
    private ParticipantUIService participantService;

    // 🟢 Step 1: Show All Quizzes
    @GetMapping("/quizzes")
    public String viewAllQuizzes(Model model) {
        List<Quiz> quizzes = participantService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "participant/quiz-list";
    }

    // 🟢 Step 2: Show Start Page for Selected Quiz
    @GetMapping("/start-quiz/{quizId}")
    public String showStartQuiz(@PathVariable Long quizId, Model model) {
        Quiz quiz = participantService.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        return "participant/start-quiz";
    }

    // 🟢 Step 3: Show Quiz Questions (Take Quiz Page)
    @GetMapping("/take-quiz/{quizId}")
    public String takeQuiz(@PathVariable Long quizId, Model model) {
        Quiz quiz = participantService.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        return "participant/take-quiz";
    }

    // 🟢 Step 4: Submit Quiz and Show Result
    @PostMapping("/submit/{quizId}")
    public String submitQuiz(@PathVariable Long quizId,
                             @RequestParam(required = false) List<String> answers,
                             Model model) {
        QuizAttempt attempt = participantService.evaluateQuiz(quizId, answers);
        model.addAttribute("score", attempt.getScore());
        model.addAttribute("quizTitle", attempt.getQuiz().getTitle());
        return "participant/quiz-result";
    }

    // 🟡 Optional: Redirect if user opens submit via GET
    @GetMapping("/submit/{quizId}")
    public String handleInvalidGet(@PathVariable Long quizId) {
        return "redirect:/participant/quizzes";
    }
}


