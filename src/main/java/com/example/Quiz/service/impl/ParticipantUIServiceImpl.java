package com.example.Quiz.service.impl;

import com.example.Quiz.entity.*;
import com.example.Quiz.repository.*;
import com.example.Quiz.service.ParticipantUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipantUIServiceImpl implements ParticipantUIService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizAttemptRepository attemptRepository;

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
    public QuizAttempt evaluateQuiz(Long quizId, List<String> answers) {
        Quiz quiz = getQuizById(quizId);
        List<Question> questions = questionRepository.findByQuizId(quizId);

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size() && questions.get(i).getCorrectAnswer().equalsIgnoreCase(answers.get(i))) {
                score++;
            }
        }

        QuizAttempt attempt = new QuizAttempt();
        attempt.setQuiz(quiz);
        attempt.setParticipantName("Guest");
        attempt.setScore(score);
        attemptRepository.save(attempt);

        return attempt;
    }
}
