package com.example.testing.app.repository;

import com.example.testing.app.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}