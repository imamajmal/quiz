package com.example.Quiz.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int timeLimit; // in minutes

    // One Quiz can have many Questions
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  
    private List<Question> questions = new ArrayList<>();

    public Quiz() {}

    public Quiz(String title, String description, int timeLimit) {
        this.title = title;
        this.description = description;
        this.timeLimit = timeLimit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTimeLimit() { return timeLimit; }
    public void setTimeLimit(int timeLimit) { this.timeLimit = timeLimit; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}

