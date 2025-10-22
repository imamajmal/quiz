package com.example.Quiz.service;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.Question;
import java.util.List;

public interface AdminService {

    // QUIZ METHODS
    List<Quiz> getAllQuizzes();
    Quiz createQuiz(Quiz quiz);
    Quiz getQuizById(Long id);
    void deleteQuiz(Long id);

    // QUESTION METHODS
    List<Question> getQuestions(Long quizId);
    Question addQuestion(Long quizId, Question question);
    Question getQuestionById(Long id);
    Question saveQuestion(Question question);
    void deleteQuestion(Long id);
}


