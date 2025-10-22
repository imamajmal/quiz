package com.example.Quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt_answers")
public class AttemptAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;
    private String selectedOption;
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    public AttemptAnswer() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public QuizAttempt getAttempt() { return attempt; }
    public void setAttempt(QuizAttempt attempt) { this.attempt = attempt; }
}

