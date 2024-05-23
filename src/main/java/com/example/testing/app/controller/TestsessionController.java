package com.example.testing.app.controller;

import com.example.testing.app.dto.TestsessionDTO;
import com.example.testing.app.service.TestsessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testsessions")
public class TestsessionController {

    private final TestsessionService service;

    public TestsessionController(TestsessionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TestsessionDTO>> getAllTestsessions() {
        return ResponseEntity.ok(service.getAllTestSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestsessionDTO> getTestsessionById(@PathVariable Integer id) {
        return service.getTestSessionById(id);
    }

    @PostMapping
    public ResponseEntity<TestsessionDTO> createTestsession(@RequestBody TestsessionDTO testSessionDTO) {
        return ResponseEntity.ok(service.createTestSession(testSessionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestsessionDTO> updateTestsession(@PathVariable Integer id, @RequestBody TestsessionDTO testSessionDTO) {
        return service.updateTestSession(id, testSessionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestsession(@PathVariable Integer id) {
        return service.deleteTestSession(id);
    }
}



