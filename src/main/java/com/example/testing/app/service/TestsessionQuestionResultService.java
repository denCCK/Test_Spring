package com.example.testing.app.service;

import com.example.testing.app.dto.TestsessionQuestionResultDTO;
import com.example.testing.app.model.TestsessionQuestionResult;
import com.example.testing.app.repository.QuestionRepository;
import com.example.testing.app.repository.TestsessionQuestionResultRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestsessionQuestionResultService {

    private final TestsessionQuestionResultRepository testsessionQuestionResultRepository;
    private final TestsessionResultRepository testsessionResultRepository;
    private final QuestionRepository questionRepository;

    public TestsessionQuestionResultService(TestsessionQuestionResultRepository testsessionQuestionResultRepository, TestsessionResultRepository testsessionResultRepositoy, QuestionRepository questionRepository) {
        this.testsessionQuestionResultRepository = testsessionQuestionResultRepository;
        this.testsessionResultRepository = testsessionResultRepositoy;
        this.questionRepository = questionRepository;
    }

    public List<TestsessionQuestionResultDTO> getAllTestsessionQuestionResults() {
        return testsessionQuestionResultRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<TestsessionQuestionResultDTO> getTestsessionQuestionResultById(Integer id) {
        Optional<TestsessionQuestionResult> testsessionQuestionResult = testsessionQuestionResultRepository.findById(id);
        return testsessionQuestionResult.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<TestsessionQuestionResultDTO> getQuestionResultsByTestsessionResultId(Integer testsessionResultId) {
        return testsessionQuestionResultRepository.findByTestsessionResultId(testsessionResultId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TestsessionQuestionResultDTO createTestsessionQuestionResult(TestsessionQuestionResultDTO testsessionQuestionResultDTO) {
        TestsessionQuestionResult testsessionQuestionResult = convertToEntity(testsessionQuestionResultDTO);
        return convertToDto(testsessionQuestionResultRepository.save(testsessionQuestionResult));
    }

    public ResponseEntity<TestsessionQuestionResultDTO> updateTestsessionQuestionResult(Integer id, TestsessionQuestionResultDTO testsessionQuestionResultDTO) {
        Optional<TestsessionQuestionResult> existingTestsessionQuestionResult = testsessionQuestionResultRepository.findById(id);
        if (existingTestsessionQuestionResult.isPresent()) {
            TestsessionQuestionResult testsessionQuestionResult = convertToEntity(testsessionQuestionResultDTO);
            testsessionQuestionResult.setId(id);
            return ResponseEntity.ok(convertToDto(testsessionQuestionResultRepository.save(testsessionQuestionResult)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTestsessionQuestionResult(Integer id) {
        Optional<TestsessionQuestionResult> result = testsessionQuestionResultRepository.findById(id);
        if (result.isPresent()) {
            testsessionQuestionResultRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TestsessionQuestionResultDTO convertToDto(TestsessionQuestionResult testsessionQuestionResult) {
        TestsessionQuestionResultDTO dto = new TestsessionQuestionResultDTO();
        dto.setTestsessionResultId(testsessionQuestionResult.getTestsessionResult().getId());
        dto.setQuestionId(testsessionQuestionResult.getQuestion().getId());
        BeanUtils.copyProperties(testsessionQuestionResult, dto);
        return dto;
    }

    private TestsessionQuestionResult convertToEntity(TestsessionQuestionResultDTO dto) {
        TestsessionQuestionResult testsessionQuestionResult = new TestsessionQuestionResult();
        testsessionQuestionResult.setTestsessionResult(testsessionResultRepository.findById(dto.getTestsessionResultId()).orElse(null));
        BeanUtils.copyProperties(dto, testsessionQuestionResult);
        testsessionQuestionResult.setQuestion(questionRepository.findById(dto.getQuestionId()).orElse(null));
        return testsessionQuestionResult;
    }
}

