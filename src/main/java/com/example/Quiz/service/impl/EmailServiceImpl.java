package com.example.Quiz.service.impl;

import com.example.Quiz.entity.EmailDetails;
import com.example.Quiz.repository.EmailRepository;
import com.example.Quiz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());
            mailMessage.setText(details.getMessage());

            javaMailSender.send(mailMessage);

            details.setSentStatus(true);
            emailRepository.save(details);

            return "Mail sent successfully to " + details.getRecipient();
        } catch (Exception e) {
            details.setSentStatus(false);
            emailRepository.save(details);
            return "Error while sending mail: " + e.getMessage();
        }
    }

    @Override
    public String sendQuizResultMail(String recipient, String quizTitle, int score) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(recipient);
        details.setSubject("Your Quiz Result: " + quizTitle);
        details.setMessage("Hi,\n\nYou completed the quiz: " + quizTitle +
                "\nYour Score: " + score + "\n\nThanks for participating!");
        details.setSentStatus(false);

        return sendSimpleMail(details);
    }
}

