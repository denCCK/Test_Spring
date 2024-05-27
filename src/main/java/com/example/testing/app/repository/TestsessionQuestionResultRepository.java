package com.example.testing.app.repository;

import com.example.testing.app.model.TestsessionQuestionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestsessionQuestionResultRepository extends JpaRepository<TestsessionQuestionResult, Integer> {
    List<TestsessionQuestionResult> findByTestsessionResultId(Integer testsessionResultId);
}


