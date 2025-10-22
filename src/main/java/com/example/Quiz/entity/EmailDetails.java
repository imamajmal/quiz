package com.example.Quiz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "email_logs")
public class EmailDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String subject;
    private String message;
    private boolean sentStatus;

    public EmailDetails() {}

    public EmailDetails(String recipient, String subject, String message, boolean sentStatus) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.sentStatus = sentStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSentStatus() { return sentStatus; }
    public void setSentStatus(boolean sentStatus) { this.sentStatus = sentStatus; }
}
