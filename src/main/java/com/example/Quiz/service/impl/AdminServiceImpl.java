package com.example.Quiz.service.impl;

import com.example.Quiz.entity.Question;
import com.example.Quiz.entity.Quiz;
import com.example.Quiz.repository.QuestionRepository;
import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // ===============================
    // ✅ QUIZ OPERATIONS
    // ===============================
    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + id));
    }

    @Override
    public void deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
        } else {
            throw new RuntimeException("Quiz not found to delete with ID: " + id);
        }
    }

    // ===============================
    // ✅ QUESTION OPERATIONS
    // ===============================
    @Override
    public List<Question> getQuestions(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    @Override
    public Question addQuestion(Long quizId, Question question) {
        Quiz quiz = getQuizById(quizId);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Question not found to delete with ID: " + id);
        }
    }
}
