package com.example.email_service.service;



import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Email;

import org.springframework.beans.factory.annotation.Value;


@Service
public class EmailServiceImpl implements com.example.email_service.service.EmailService {

    @Value("${mailjet.api.key}")
    private String mailjetApiKey;

    @Value("${mailjet.secret.key}")
    private String mailjetSecretKey;

    @Value("${mailjet.sender.email}")
    private String senderEmail;

    @Override
    public void sendWelcomeEmail(String toEmail) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client = new MailjetClient(mailjetApiKey, mailjetSecretKey);
        MailjetRequest request = new MailjetRequest(Email.resource)
                .property(Email.FROMEMAIL, senderEmail)
                .property(Email.FROMNAME, "Hi, This is James!")
                .property(Email.SUBJECT, "Welcome to my platform!")
                .property(Email.TEXTPART, "Dear User,\n\nWelcome to my platform.\n\nThank you for registering.\nJames Yu")
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject().put("Email", toEmail)));

        client.post(request);
        System.out.println("Email sent successfully to " + toEmail);
    }
}
