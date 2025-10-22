package com.example.Quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;
    private String selectedOption;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    // Getters & Setters
    public Long getId() { return id; }
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public QuizAttempt getAttempt() { return attempt; }
    public void setAttempt(QuizAttempt attempt) { this.attempt = attempt; }
}

