package com.example.Quiz.service;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.QuizAttempt;
import java.util.List;

public interface ParticipantUIService {
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long quizId);
    QuizAttempt evaluateQuiz(Long quizId, List<String> answers);
}

