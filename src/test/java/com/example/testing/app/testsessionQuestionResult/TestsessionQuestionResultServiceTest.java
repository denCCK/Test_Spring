package com.example.testing.app.testsessionQuestionResult;
import com.example.testing.app.dto.TestsessionQuestionResultDTO;
import com.example.testing.app.model.TestsessionQuestionResult;
import com.example.testing.app.repository.QuestionRepository;
import com.example.testing.app.repository.TestsessionQuestionResultRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import com.example.testing.app.service.TestsessionQuestionResultService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TestsessionQuestionResultServiceTest {

    @MockBean
    private TestsessionQuestionResultRepository testsessionQuestionResultRepository;
    @MockBean
    private TestsessionResultRepository testsessionResultRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void testGetAllTestsessionQuestionResults() {
        List<TestsessionQuestionResult> testsessionQuestionResults = List.of(new TestsessionQuestionResult(), new TestsessionQuestionResult());
        List<TestsessionQuestionResultDTO> expectedDTOs = testsessionQuestionResults.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Mockito.when(testsessionQuestionResultRepository.findAll()).thenReturn(testsessionQuestionResults);

        TestsessionQuestionResultService testsessionQuestionResultService = new TestsessionQuestionResultService(testsessionQuestionResultRepository, testsessionResultRepository, questionRepository);
        List<TestsessionQuestionResultDTO> actualDTOs = testsessionQuestionResultService.getAllTestsessionQuestionResults();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        assertTrue(expectedDTOs.containsAll(actualDTOs) && actualDTOs.containsAll(expectedDTOs));
    }

    @Test
    void testGetTestsessionQuestionResultById_Found() {
        TestsessionQuestionResult testsessionQuestionResult = new TestsessionQuestionResult();
        testsessionQuestionResult.setId(1);
        TestsessionQuestionResultDTO expectedDTO = convertToDTO(testsessionQuestionResult);

        Mockito.when(testsessionQuestionResultRepository.findById(1)).thenReturn(Optional.of(testsessionQuestionResult));

        TestsessionQuestionResultService testsessionQuestionResultService = new TestsessionQuestionResultService(testsessionQuestionResultRepository, testsessionResultRepository, questionRepository);
        ResponseEntity<TestsessionQuestionResultDTO> responseEntity = testsessionQuestionResultService.getTestsessionQuestionResultById(1);

        assertEquals(expectedDTO, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetTestsessionQuestionResultById_NotFound() {
        Mockito.when(testsessionQuestionResultRepository.findById(1)).thenReturn(Optional.empty());

        TestsessionQuestionResultService testsessionQuestionResultService = new TestsessionQuestionResultService(testsessionQuestionResultRepository, testsessionResultRepository, questionRepository);
        ResponseEntity<TestsessionQuestionResultDTO> responseEntity = testsessionQuestionResultService.getTestsessionQuestionResultById(1);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    private TestsessionQuestionResultDTO convertToDTO(TestsessionQuestionResult testsessionQuestionResult) {
        TestsessionQuestionResultDTO dto = new TestsessionQuestionResultDTO();
        BeanUtils.copyProperties(testsessionQuestionResult, dto);
        return dto;
    }
}

