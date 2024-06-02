package com.example.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public String sendEmail(String sub, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("YOUR_EMAIL");
        message.setTo("YOUR_EMAIL");
        message.setSubject(sub);
        message.setText(text);

        javaMailSender.send(message);
        return "Sent successfully!";
    }
}
