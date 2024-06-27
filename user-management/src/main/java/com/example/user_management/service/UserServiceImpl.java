package com.example.user_management.service;

import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Autowired
    private RestTemplate restTemplate; // Or use WebClient

    // REST API endpoint
    private static final String EMAIL_SERVICE_URL = "http://localhost:8081/api/sendWelcomeEmail"; // Assuming email-service runs on localhost:8081


    @Override
    public User createUser(User user) throws MailjetSocketTimeoutException, MailjetException {
        User savedUser = userRepository.save(user); // Save the user to the database

        // Build request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userEmail", savedUser.getEmail());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request to the email service
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(EMAIL_SERVICE_URL, requestEntity, Void.class);

        // Check the response status code and handle success or failure as needed

        return savedUser; // Return the saved user object
    }



    @Override
    public User updateUser(User user) {
        // Check if the user exists in the database
        Optional<User> existingUserOptional = userRepository.findById(user.getId());
        if (existingUserOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }

        // Update user details
        User existingUser = existingUserOptional.get();
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setActive(user.isActive());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    @Override
    public User deactivateUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        User user = userOptional.get();
        user.setActive(false); // Deactivate the user
        return userRepository.save(user); // Save and return the updated user
    }

    @Override
    public void deleteUser(Long id) {
        // Check if the user exists in the database
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        // Delete the user
        userRepository.deleteById(id);
    }

}