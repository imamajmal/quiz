package com.example.Quiz.controller;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.Question;
import com.example.Quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // ✅ Create new Quiz
    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    // ✅ Get all quizzes
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // ✅ Get quiz by ID
    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    // ✅ Update quiz
    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }

    // ✅ Delete quiz
    @DeleteMapping("/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return "Quiz deleted successfully!";
    }

    // ✅ Add Question to a Quiz
    @PostMapping("/{quizId}/questions")
    public Question addQuestionToQuiz(@PathVariable Long quizId, @RequestBody Question question) {
        return quizService.addQuestionToQuiz(quizId, question);
    }

    // ✅ Get all Questions for a Quiz
    @GetMapping("/{quizId}/questions")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return quizService.getQuestionsByQuizId(quizId);
    }

    // ✅ Delete a Question
    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId) {
        quizService.deleteQuestion(questionId);
        return "Question deleted successfully!";
    }
}

