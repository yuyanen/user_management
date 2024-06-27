package com.example.email_service.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;


public interface EmailService {
    void sendWelcomeEmail(String toEmail) throws MailjetException, MailjetSocketTimeoutException;
}
