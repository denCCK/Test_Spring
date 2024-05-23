package com.example.testing.app.service;

import com.example.testing.app.dto.TestsessionResultDTO;
import com.example.testing.app.model.TestsessionResult;
import com.example.testing.app.repository.TestsessionRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestsessionResultService {

    private final TestsessionResultRepository testsessionResultRepository;
    private final TestsessionRepository testSessionRepository;

    public TestsessionResultService(TestsessionResultRepository testsessionResultRepository, TestsessionRepository testSessionRepository) {
        this.testsessionResultRepository = testsessionResultRepository;
        this.testSessionRepository = testSessionRepository;
    }


    public List<TestsessionResultDTO> getAllTestsessionResults() {
        return testsessionResultRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<TestsessionResultDTO> getTestsessionResultById(Integer id) {
        Optional<TestsessionResult> testsessionResult = testsessionResultRepository.findById(id);
        return testsessionResult.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public TestsessionResultDTO createTestsessionResult(TestsessionResultDTO testsessionResultDTO) {
        TestsessionResult testsessionResult = convertToEntity(testsessionResultDTO);
        return convertToDto(testsessionResultRepository.save(testsessionResult));
    }

    public ResponseEntity<TestsessionResultDTO> updateTestsessionResult(Integer id, TestsessionResultDTO testsessionResultDTO) {
        Optional<TestsessionResult> existingTestsessionResult = testsessionResultRepository.findById(id);
        if (existingTestsessionResult.isPresent()) {
            TestsessionResult testsessionResult = convertToEntity(testsessionResultDTO);
            testsessionResult.setId(id);
            return ResponseEntity.ok(convertToDto(testsessionResultRepository.save(testsessionResult)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTestsessionResult(Integer id) {
        Optional<TestsessionResult> result = testsessionResultRepository.findById(id);
        if (result.isPresent()) {
            testsessionResultRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TestsessionResultDTO convertToDto(TestsessionResult testsessionResult) {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setTestsessionId(testsessionResult.getTestsession().getId());
        BeanUtils.copyProperties(testsessionResult, dto);
        return dto;
    }

    private TestsessionResult convertToEntity(TestsessionResultDTO dto) {
        TestsessionResult testsessionResult = new TestsessionResult();
        testsessionResult.setTestsession(testSessionRepository.findById(dto.getTestsessionId()).orElse(null));
        BeanUtils.copyProperties(dto, testsessionResult);
        return testsessionResult;
    }
}

