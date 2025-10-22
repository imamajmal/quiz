package com.example.Quiz.controller;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.Question;
import com.example.Quiz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminUIController {

    @Autowired
    private AdminService adminService;

    // ===============================
    // ✅ 1. ADMIN DASHBOARD — VIEW ALL QUIZZES
    // ===============================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("quizzes", adminService.getAllQuizzes());
        return "quiz-list";
    }

    // ===============================
    // ✅ 2. CREATE NEW QUIZ
    // ===============================
    @GetMapping("/create-quiz")
    public String showCreateQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "create-quiz";
    }

    @PostMapping("/create-quiz")
    public String createQuiz(@ModelAttribute Quiz quiz) {
        adminService.createQuiz(quiz);
        return "redirect:/admin/dashboard";
    }

    // ===============================
    // ✅ 3. EDIT QUIZ
    // ===============================
    @GetMapping("/quiz/edit/{quizId}")
    public String showEditQuizForm(@PathVariable Long quizId, Model model) {
        Quiz quiz = adminService.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        return "edit-quiz";
    }

    @PostMapping("/quiz/update/{quizId}")
    public String updateQuiz(@PathVariable Long quizId, @ModelAttribute Quiz quiz) {
        Quiz existingQuiz = adminService.getQuizById(quizId);
        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setDescription(quiz.getDescription());
        existingQuiz.setTimeLimit(quiz.getTimeLimit());
        adminService.createQuiz(existingQuiz);
        return "redirect:/admin/dashboard";
    }

    // ===============================
    // ✅ 4. DELETE QUIZ
    // ===============================
    @GetMapping("/delete/{quizId}")
    public String deleteQuiz(@PathVariable Long quizId) {
        adminService.deleteQuiz(quizId);
        return "redirect:/admin/dashboard";
    }

    // ===============================
    // ✅ 5. VIEW QUESTIONS OF A QUIZ
    // ===============================
    @GetMapping("/quiz/{quizId}/questions")
    public String showQuestions(@PathVariable Long quizId, Model model) {
        model.addAttribute("questions", adminService.getQuestions(quizId));
        model.addAttribute("quizId", quizId);
        return "question-list";
    }

    // ===============================
    // ✅ 6. ADD QUESTION TO QUIZ
    // ===============================
    @GetMapping("/quiz/{quizId}/add-question")
    public String showAddQuestion(@PathVariable Long quizId, Model model) {
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", new Question());
        return "create-question";
    }

    @PostMapping("/quiz/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId, @ModelAttribute Question question) {
        adminService.addQuestion(quizId, question);
        return "redirect:/admin/quiz/" + quizId + "/questions";
    }

    // ===============================
    // ✅ 7. EDIT QUESTION
    // ===============================
    @GetMapping("/quiz/{quizId}/edit-question/{questionId}")
    public String showEditQuestion(@PathVariable Long quizId,
                                   @PathVariable Long questionId,
                                   Model model) {
        Question question = adminService.getQuestionById(questionId);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", question);
        return "edit-question";
    }

    @PostMapping("/quiz/{quizId}/update-question/{questionId}")
    public String updateQuestion(@PathVariable Long quizId,
                                 @PathVariable Long questionId,
                                 @ModelAttribute Question updatedQuestion) {
        Question existing = adminService.getQuestionById(questionId);
        existing.setQuestionText(updatedQuestion.getQuestionText());
        existing.setOptionA(updatedQuestion.getOptionA());
        existing.setOptionB(updatedQuestion.getOptionB());
        existing.setOptionC(updatedQuestion.getOptionC());
        existing.setOptionD(updatedQuestion.getOptionD());
        existing.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
        adminService.saveQuestion(existing);
        return "redirect:/admin/quiz/" + quizId + "/questions";
    }

    // ===============================
    // ✅ 8. DELETE QUESTION
    // ===============================
    @GetMapping("/delete-question/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId) {
        adminService.deleteQuestion(questionId);
        return "redirect:/admin/dashboard";
    }
}
