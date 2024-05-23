package com.example.testing.app.controller;

import com.example.testing.app.dto.TestsessionQuestionResultDTO;
import com.example.testing.app.service.TestsessionQuestionResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testsession-question-results")
public class TestsessionQuestionResultController {

    private final TestsessionQuestionResultService testsessionQuestionResultService;

    public TestsessionQuestionResultController(TestsessionQuestionResultService testsessionQuestionResultService) {
        this.testsessionQuestionResultService = testsessionQuestionResultService;
    }

    @GetMapping
    public List<TestsessionQuestionResultDTO> getAllTestsessionQuestionResults() {
        return testsessionQuestionResultService.getAllTestsessionQuestionResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestsessionQuestionResultDTO> getTestsessionQuestionResultById(@PathVariable Integer id) {
        return testsessionQuestionResultService.getTestsessionQuestionResultById(id);
    }

    @PostMapping
    public TestsessionQuestionResultDTO createTestsessionQuestionResult(@RequestBody TestsessionQuestionResultDTO testsessionQuestionResultDTO) {
        return testsessionQuestionResultService.createTestsessionQuestionResult(testsessionQuestionResultDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestsessionQuestionResultDTO> updateTestsessionQuestionResult(@PathVariable Integer id, @RequestBody TestsessionQuestionResultDTO testsessionQuestionResultDTO) {
        return testsessionQuestionResultService.updateTestsessionQuestionResult(id, testsessionQuestionResultDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTestsessionQuestionResult(@PathVariable Integer id) {
        testsessionQuestionResultService.deleteTestsessionQuestionResult(id);
    }
}

