package com.example.Quiz.service;

import com.example.Quiz.entity.QuizAttempt;
import java.util.List;
import java.util.Map;

public interface QuizAttemptService {
    QuizAttempt startQuizAttempt(Long quizId, String participantName);
    QuizAttempt submitQuizAttempt(Long attemptId, Map<Long, String> answers);
    List<QuizAttempt> getAttemptsByParticipant(String participantName);
}

