package com.example.email_service.service;



import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.resource.Email;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmailServiceImpl implements EmailService {

    @Value("${mailjet.api.key}")
    private String mailjetApiKey;

    @Value("${mailjet.sender.email}")
    private String senderEmail;

    @Value("${mailjet.secret.key}")
    private String mailjetSecretKey;

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

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

        MailjetResponse response = client.post(request);
        if (response.getStatus() == 200) {
            logger.info("Email sent successfully to " + toEmail);
        } else {
            logger.error("Failed to send email to " + toEmail + ". Status code: " + response.getStatus());
        }

    }
}