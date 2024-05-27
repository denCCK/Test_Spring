package com.example.testing.app.service;

import com.example.testing.app.dto.TestsessionDTO;
import com.example.testing.app.model.Grade;
import com.example.testing.app.model.Testsession;
import com.example.testing.app.repository.TestRepository;
import com.example.testing.app.repository.TestsessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestsessionService {

    private final TestsessionRepository testSessionRepository;
    private final TestRepository testRepository;

    public TestsessionService(TestsessionRepository testSessionRepository, TestRepository testRepository) {
        this.testSessionRepository = testSessionRepository;
        this.testRepository = testRepository;
    }

    public List<TestsessionDTO> getAllTestSessions() {
        return testSessionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<TestsessionDTO> getTestSessionById(Integer id) {
        Optional<Testsession> testSession = testSessionRepository.findById(id);
        return testSession.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public TestsessionDTO createTestSession(TestsessionDTO testsessionDTO) {
        Testsession testsession = convertToEntity(testsessionDTO);
        return convertToDto(testSessionRepository.save(testsession));
    }

    public ResponseEntity<TestsessionDTO> updateTestSession(Integer id, TestsessionDTO testsessionDTO) {
        Optional<Testsession> existingTestSession = testSessionRepository.findById(id);
        if (existingTestSession.isPresent()) {
            Testsession testsession = convertToEntity(testsessionDTO);
            testsession.setId(id);
            return ResponseEntity.ok(convertToDto(testSessionRepository.save(testsession)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTestSession(Integer id) {
        Optional<Testsession> testSession = testSessionRepository.findById(id);
        if (testSession.isPresent()) {
            testSessionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TestsessionDTO convertToDto(Testsession testSession) {
        TestsessionDTO dto = new TestsessionDTO();
        dto.setId(testSession.getId());
        dto.setTestsessionName(testSession.getTestsessionName());
        dto.setTestsessionDescription(testSession.getTestsessionDescription());
        dto.setStartDate(testSession.getStartDate());
        dto.setEndDate(testSession.getEndDate());
        dto.setTestId(testSession.getTest().getId());
        dto.setCreationDate(testSession.getCreationDate());
        dto.setLastChangeDate(testSession.getLastChangeDate());

        //dto.setGrades(testSession.getGrades());

        return dto;
    }

    private Testsession convertToEntity(TestsessionDTO dto) {

        Testsession testSession = new Testsession();
        testSession.setId(dto.getId());
        testSession.setTestsessionName(dto.getTestsessionName());
        testSession.setTestsessionDescription(dto.getTestsessionDescription());
        testSession.setStartDate(dto.getStartDate());
        testSession.setEndDate(dto.getEndDate());

        testSession.setTest(testRepository.findById(dto.getTestId()).orElse(null));
        testSession.setCreationDate(dto.getCreationDate());
        testSession.setLastChangeDate(dto.getLastChangeDate());

        //testSession.setGrades(dto.getGrades());

        return testSession;
    }
}


