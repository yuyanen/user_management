package com.example.email_service.controller;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email_service.service.EmailService;


@RestController
@RequestMapping("/api/users")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendWelcomeEmail")
    public ResponseEntity<String> sendWelcomeEmail(@RequestBody String toEmail) {
        System.out.println("EmailController: sendWelcomeEmail endpoint called with email: " + toEmail);
        try {
            emailService.sendWelcomeEmail(toEmail);
            return ResponseEntity.ok("Email sent successfully to " + toEmail);
        } catch (MailjetException | MailjetSocketTimeoutException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}