package com.example.testing.app.controller;

import com.example.testing.app.dto.TestsessionResultDTO;
import com.example.testing.app.model.TestsessionResult;
import com.example.testing.app.service.TestsessionResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testsession-results")
public class TestsessionResultController {

    private final TestsessionResultService testsessionResultService;

    public TestsessionResultController(TestsessionResultService testsessionResultService) {
        this.testsessionResultService = testsessionResultService;
    }

    @GetMapping
    public List<TestsessionResultDTO> getAllTestsessionResults() {
        return testsessionResultService.getAllTestsessionResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestsessionResultDTO> getTestsessionResultById(@PathVariable Integer id) {
        return testsessionResultService.getTestsessionResultById(id);
    }

    @GetMapping("/testsession/{id}")
    public List<TestsessionResultDTO> getTestsessionResultsByTestsessionId(@PathVariable Integer id) {
        return testsessionResultService.getTestsessionResultsByTestsessionId(id);
    }

    @PostMapping
    public TestsessionResultDTO createTestsessionResult(@RequestBody TestsessionResultDTO testsessionResultDTO) {
        return testsessionResultService.createTestsessionResult(testsessionResultDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestsessionResultDTO> updateTestsessionResult(@PathVariable Integer id, @RequestBody TestsessionResultDTO testsessionResultDTO) {
        return testsessionResultService.updateTestsessionResult(id, testsessionResultDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTestsessionResult(@PathVariable Integer id) {
        testsessionResultService.deleteTestsessionResult(id);
    }
}

