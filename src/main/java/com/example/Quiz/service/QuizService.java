package com.example.Quiz.service;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.Question;
import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id);
    Quiz updateQuiz(Long id, Quiz quiz);
    void deleteQuiz(Long id);

    // Question Management
    Question addQuestionToQuiz(Long quizId, Question question);
    List<Question> getQuestionsByQuizId(Long quizId);
    void deleteQuestion(Long questionId);
}

