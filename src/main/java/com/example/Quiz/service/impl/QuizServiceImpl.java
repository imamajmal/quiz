package com.example.Quiz.service.impl;

import com.example.Quiz.entity.Quiz;
import com.example.Quiz.entity.Question;
import com.example.Quiz.repository.QuizRepository;
import com.example.Quiz.repository.QuestionRepository;
import com.example.Quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz existing = quizRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(updatedQuiz.getTitle());
            existing.setDescription(updatedQuiz.getDescription());
            existing.setTimeLimit(updatedQuiz.getTimeLimit());
            return quizRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public Question addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            question.setQuiz(quiz);
            return questionRepository.save(question);
        }
        return null;
    }

    @Override
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}

