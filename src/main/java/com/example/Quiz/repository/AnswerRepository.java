package com.example.Quiz.repository;

import com.example.Quiz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> { }
