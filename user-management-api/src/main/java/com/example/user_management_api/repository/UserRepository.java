package com.example.user_management_api.repository;


import com.example.user_management_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdAndActiveTrue(Long id);
}