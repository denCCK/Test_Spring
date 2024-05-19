package com.example.testing.app.repository;

import com.example.testing.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}