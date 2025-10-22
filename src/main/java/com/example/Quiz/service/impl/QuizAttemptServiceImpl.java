package com.example.Quiz.service.impl;

import com.example.Quiz.entity.*;
import com.example.Quiz.repository.*;
import com.example.Quiz.service.QuizAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private AttemptAnswerRepository attemptAnswerRepository;

    @Override
    public QuizAttempt startQuizAttempt(Long quizId, String participantName) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) {
            throw new RuntimeException("Quiz not found!");
        }

        QuizAttempt attempt = new QuizAttempt();
        attempt.setQuiz(quiz);
        attempt.setParticipantName(participantName);
        attempt.setStartTime(LocalDateTime.now());
        return quizAttemptRepository.save(attempt);
    }

    @Override
    public QuizAttempt submitQuizAttempt(Long attemptId, Map<Long, String> answers) {
        QuizAttempt attempt = quizAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found!"));

        int score = 0;

        for (Map.Entry<Long, String> entry : answers.entrySet()) {
            Long questionId = entry.getKey();
            String selectedOption = entry.getValue();

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found!"));

            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(selectedOption);

            if (isCorrect) {
                score++;
            }

            AttemptAnswer attemptAnswer = new AttemptAnswer();
            attemptAnswer.setQuestionId(questionId);
            attemptAnswer.setSelectedOption(selectedOption);
            attemptAnswer.setCorrect(isCorrect);
            attemptAnswer.setAttempt(attempt);
            attemptAnswerRepository.save(attemptAnswer);
        }

        attempt.setEndTime(LocalDateTime.now());
        attempt.setScore(score);
        return quizAttemptRepository.save(attempt);
    }

    @Override
    public List<QuizAttempt> getAttemptsByParticipant(String participantName) {
        return quizAttemptRepository.findByParticipantName(participantName);
    }
}

