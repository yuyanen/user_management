package com.example.user_management_api.service;



import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mailjet.api.key}")
    private String mailjetApiKey;

    @Value("${mailjet.secret.key}")
    private String mailjetSecretKey;

    @Value("${mailjet.sender.email}")
    private String senderEmail;

    public void sendWelcomeEmail(String toEmail) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client = new MailjetClient(mailjetApiKey, mailjetSecretKey);
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Email.FROMEMAIL, senderEmail)
                .property(Email.FROMNAME, "Hi,This is James!")
                .property(Email.SUBJECT, "Welcome to my platform!")
                .property(Email.TEXTPART, "Dear User,\n\n Welcome to my platform. \n\n\nThank you for registering.\n James Yu")
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject().put("Email", toEmail)));

        client.post(request);
        System.out.println("Email sent successfully to " + toEmail);
    }
}