package com.example.user_management.service;


import com.example.user_management.model.User;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user) throws MailjetSocketTimeoutException, MailjetException;

    User updateUser(User user);

    void deleteUser(Long id);

    User deactivateUser(Long id);

}
