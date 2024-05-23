package com.example.testing.app.service;

import com.example.testing.app.model.Test;
import com.example.testing.app.model.User;
import com.example.testing.app.repository.TestRepository;
import com.example.testing.app.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public ResponseEntity<Test> getTestById(Integer id) {
        Optional<Test> test = testRepository.findById(id);
        return test.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public ResponseEntity<Test> updateTest(Integer id, Test test) {
        Optional<Test> existingTest = testRepository.findById(id);
        if (existingTest.isPresent()) {
            test.setId(id);
            return ResponseEntity.ok(testRepository.save(test));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTest(Integer id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            testRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

