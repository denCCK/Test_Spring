package com.example.testing.app.controller;

import com.example.testing.app.dto.TestsessionAnswerResultDTO;
import com.example.testing.app.service.TestsessionAnswerResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testsession-answer-results")
public class TestsessionAnswerResultController {

    private final TestsessionAnswerResultService answerResultService;

    public TestsessionAnswerResultController(TestsessionAnswerResultService answerResultService) {
        this.answerResultService = answerResultService;
    }

    @GetMapping
    public List<TestsessionAnswerResultDTO> getAllAnswerResults() {
        return answerResultService.getAllAnswersResult();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestsessionAnswerResultDTO> getAnswerResultById(@PathVariable Integer id) {
        return answerResultService.getAnswerResultById(id);
    }

    @PostMapping
    public TestsessionAnswerResultDTO createAnswerResult(@RequestBody TestsessionAnswerResultDTO answerResultDTO) {
        return answerResultService.createAnswerResult(answerResultDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestsessionAnswerResultDTO> updateAnswerResult(@PathVariable Integer id, @RequestBody TestsessionAnswerResultDTO answerResultDTO) {
        return answerResultService.updateAnswerResult(id, answerResultDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswerResult(@PathVariable Integer id) {
        answerResultService.deleteAnswerResult(id);
    }
}

