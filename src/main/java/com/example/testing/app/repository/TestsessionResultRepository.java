package com.example.testing.app.repository;

import com.example.testing.app.model.TestsessionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestsessionResultRepository extends JpaRepository<TestsessionResult, Integer> {
}
