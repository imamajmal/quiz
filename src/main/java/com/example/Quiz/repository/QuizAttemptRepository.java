package com.example.Quiz.repository;

import com.example.Quiz.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByParticipantName(String participantName);
}

