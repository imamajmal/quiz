package com.example.Quiz.service;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.QuizAttempt;
import java.util.List;

public interface ParticipantService {

    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long quizId);

    QuizAttempt startQuiz(String participantName, Long quizId);
    QuizAttempt submitQuiz(Long attemptId, List<String> answers);
}

