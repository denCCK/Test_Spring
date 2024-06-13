package com.example.testing.app.service;

import com.example.testing.app.dto.TestsessionAnswerResultDTO;
import com.example.testing.app.dto.TestsessionDTO;
import com.example.testing.app.model.Testsession;
import com.example.testing.app.model.TestsessionAnswerResult;
import com.example.testing.app.repository.AnswerRepository;
import com.example.testing.app.repository.QuestionRepository;
import com.example.testing.app.repository.TestsessionAnswerResultRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestsessionAnswerResultService {

    private final TestsessionAnswerResultRepository answerResultRepository;
    private final TestsessionResultRepository sessionResultRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public TestsessionAnswerResultService(TestsessionAnswerResultRepository answerResultRepository,
                                          TestsessionResultRepository sessionResultRepository,
                                          AnswerRepository answerRepository,
                                          QuestionRepository questionRepository) {
        this.answerResultRepository = answerResultRepository;
        this.sessionResultRepository = sessionResultRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public List<TestsessionAnswerResultDTO> getAllAnswersResult() {
        return answerResultRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<TestsessionAnswerResultDTO> getAnswerResultById(Integer id) {
        Optional<TestsessionAnswerResult> answer = answerResultRepository.findById(id);
        return answer.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public List<TestsessionAnswerResultDTO> getAnswerResultsByTestsessionResultId(Integer testsessionResultId) {
        return answerResultRepository.findByTestsessionResultId(testsessionResultId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TestsessionAnswerResultDTO createAnswerResult(TestsessionAnswerResultDTO answerResultDTO) {
        TestsessionAnswerResult answerResult = convertToEntity(answerResultDTO);
        return convertToDto(answerResultRepository.save(answerResult));
    }

    public ResponseEntity<TestsessionAnswerResultDTO> updateAnswerResult(Integer id, TestsessionAnswerResultDTO answerResultDTO) {
        Optional<TestsessionAnswerResult> existingAnswer = answerResultRepository.findById(id);
        if (existingAnswer.isPresent()) {
            TestsessionAnswerResult answerResult = convertToEntity(answerResultDTO);
            answerResult.setId(id);
            return ResponseEntity.ok(convertToDto(answerResultRepository.save(answerResult)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteAnswerResult(Integer id) {
        Optional<TestsessionAnswerResult> answer = answerResultRepository.findById(id);
        if (answer.isPresent()) {
            answerResultRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TestsessionAnswerResultDTO convertToDto(TestsessionAnswerResult testsessionAnswerResult) {
        TestsessionAnswerResultDTO dto = new TestsessionAnswerResultDTO();
        dto.setId(testsessionAnswerResult.getId());
        dto.setAnswerId(testsessionAnswerResult.getAnswer().getId());
        dto.setQuestionId(testsessionAnswerResult.getQuestion().getId());
        dto.setTestsessionResultId(testsessionAnswerResult.getTestsessionResult().getId());
        dto.setAnswerText(testsessionAnswerResult.getAnswerText());
        dto.setAnswerImg(testsessionAnswerResult.getAnswerImg());
        dto.setIsCorrect(testsessionAnswerResult.getIsCorrect());
        dto.setComplianceText(testsessionAnswerResult.getComplianceText());
        dto.setComplianceImg(testsessionAnswerResult.getComplianceImg());
        dto.setAnswerFormula(testsessionAnswerResult.getAnswerFormula());
        dto.setIsFormula(testsessionAnswerResult.getIsFormula());
        dto.setIsComplianceFormula(testsessionAnswerResult.getIsComplianceFormula());
        return dto;
    }

    private TestsessionAnswerResult convertToEntity(TestsessionAnswerResultDTO dto) {

        TestsessionAnswerResult testsessionAnswerResult = new TestsessionAnswerResult();
        testsessionAnswerResult.setId(dto.getId());
        testsessionAnswerResult.setAnswer(answerRepository.findById(dto.getAnswerId()).orElse(null));
        testsessionAnswerResult.setQuestion(questionRepository.findById(dto.getQuestionId()).orElse(null));
        testsessionAnswerResult.setTestsessionResult(sessionResultRepository.findById(dto.getTestsessionResultId()).orElse(null));
        testsessionAnswerResult.setAnswerText(dto.getAnswerText());
        testsessionAnswerResult.setAnswerImg(dto.getAnswerImg());
        testsessionAnswerResult.setIsCorrect(dto.getIsCorrect());
        testsessionAnswerResult.setComplianceText(dto.getComplianceText());
        testsessionAnswerResult.setComplianceImg(dto.getComplianceImg());
        testsessionAnswerResult.setAnswerFormula(dto.getAnswerFormula());
        testsessionAnswerResult.setIsFormula(dto.getIsFormula());
        testsessionAnswerResult.setIsComplianceFormula(dto.getIsComplianceFormula());
        return testsessionAnswerResult;
    }
}