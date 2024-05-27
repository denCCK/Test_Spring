package com.example.testing.app.repository;

import com.example.testing.app.model.TestsessionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestsessionResultRepository extends JpaRepository<TestsessionResult, Integer> {
    List<TestsessionResult> findByTestsessionId(Integer testsessionId);
}
