package com.example.testing.app.repository;

import com.example.testing.app.model.TestsessionAnswerResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestsessionAnswerResultRepository extends JpaRepository<TestsessionAnswerResult, Integer> {
    List<TestsessionAnswerResult> findByTestsessionResultId(Integer testsessionResultId);
}

