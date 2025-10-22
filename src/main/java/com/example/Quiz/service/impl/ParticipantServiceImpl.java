package com.example.Quiz.service.impl;

import com.example.Quiz.entity.*;
import com.example.Quiz.repository.*;
import com.example.Quiz.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizAttemptRepository attemptRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    @Override
    public QuizAttempt startQuiz(String participantName, Long quizId) {
        Quiz quiz = getQuizById(quizId);
        QuizAttempt attempt = new QuizAttempt();
        attempt.setQuiz(quiz);
        attempt.setParticipantName(participantName);
        attempt.setStartTime(LocalDateTime.now());
        return attemptRepository.save(attempt);
    }

    @Override
    public QuizAttempt submitQuiz(Long attemptId, List<String> answers) {
        QuizAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        List<Question> questions = questionRepository.findByQuizId(attempt.getQuiz().getId());
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = answers.get(i);
            boolean correct = question.getCorrectAnswer().equalsIgnoreCase(userAnswer);

            Answer ans = new Answer();
            ans.setQuestionId(question.getId());
            ans.setSelectedOption(userAnswer);
            ans.setCorrect(correct);
            ans.setAttempt(attempt);
            answerRepository.save(ans);

            if (correct) score++;
        }

        attempt.setScore(score);
        attempt.setEndTime(LocalDateTime.now());
        return attemptRepository.save(attempt);
    }
}

