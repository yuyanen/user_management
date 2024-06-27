package com.example.user_management_api.service;

import com.example.user_management_api.model.User;
import com.example.user_management_api.repository.UserRepository;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // Inject EmailService

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) throws MessagingException, IOException, MailjetSocketTimeoutException, MailjetException {
        User savedUser = userRepository.save(user); // Save the user
        emailService.sendWelcomeEmail(savedUser.getEmail()); // Call sendWelcomeEmail with user's email
        return savedUser;
    }

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

    public User deactivateUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        User user = userOptional.get();
        user.setActive(false); // Deactivate the user
        return userRepository.save(user); // Save and return the updated user
    }

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