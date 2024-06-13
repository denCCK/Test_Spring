package com.example.testing.app.testsessionAnswerResult;
import com.example.testing.app.dto.TestsessionAnswerResultDTO;
import com.example.testing.app.model.TestsessionAnswerResult;
import com.example.testing.app.repository.AnswerRepository;
import com.example.testing.app.repository.QuestionRepository;
import com.example.testing.app.repository.TestsessionAnswerResultRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import com.example.testing.app.service.TestsessionAnswerResultService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class TestsessionAnswerResultServiceTest {

    @MockBean
    private TestsessionAnswerResultRepository answerResultRepository;

    @MockBean
    private TestsessionResultRepository sessionResultRepository;

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void testGetAllAnswersResult() {
        List<TestsessionAnswerResult> answerResults = List.of(new TestsessionAnswerResult(), new TestsessionAnswerResult());
        List<TestsessionAnswerResultDTO> expectedDTOs = answerResults.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Mockito.when(answerResultRepository.findAll()).thenReturn(answerResults);

        TestsessionAnswerResultService answerResultService = new TestsessionAnswerResultService(answerResultRepository, sessionResultRepository, answerRepository, questionRepository);
        List<TestsessionAnswerResultDTO> actualDTOs = answerResultService.getAllAnswersResult();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        assertTrue(expectedDTOs.containsAll(actualDTOs) && actualDTOs.containsAll(expectedDTOs));
    }

    @Test
    void testGetAnswerResultById_Found() {
        TestsessionAnswerResult answerResult = new TestsessionAnswerResult();
        answerResult.setId(1);
        TestsessionAnswerResultDTO expectedDTO = convertToDTO(answerResult);

        Mockito.when(answerResultRepository.findById(1)).thenReturn(Optional.of(answerResult));

        TestsessionAnswerResultService answerResultService = new TestsessionAnswerResultService(answerResultRepository, sessionResultRepository, answerRepository, questionRepository);
        ResponseEntity<TestsessionAnswerResultDTO> responseEntity = answerResultService.getAnswerResultById(1);

        assertEquals(expectedDTO, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetAnswerResultById_NotFound() {
        Mockito.when(answerResultRepository.findById(1)).thenReturn(Optional.empty());

        TestsessionAnswerResultService answerResultService = new TestsessionAnswerResultService(answerResultRepository, sessionResultRepository, answerRepository, questionRepository);
        ResponseEntity<TestsessionAnswerResultDTO> responseEntity = answerResultService.getAnswerResultById(1);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    private TestsessionAnswerResultDTO convertToDTO(TestsessionAnswerResult testsessionAnswerResult) {
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
}

