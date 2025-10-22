package com.example.Quiz.repository;

import com.example.Quiz.entity.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailDetails, Long> {
}

