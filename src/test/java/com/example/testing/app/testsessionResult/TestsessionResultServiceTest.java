package com.example.testing.app.testsessionResult;

import com.example.testing.app.dto.TestsessionResultDTO;
import com.example.testing.app.model.TestsessionResult;
import com.example.testing.app.repository.TestsessionRepository;
import com.example.testing.app.repository.TestsessionResultRepository;
import com.example.testing.app.service.TestsessionResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsessionResultServiceTest {

    @Mock
    private TestsessionResultRepository testsessionResultRepository;

    @Mock
    private TestsessionRepository testSessionRepository;

    @InjectMocks
    private TestsessionResultService testsessionResultService;

    private TestsessionResult testsessionResult;
    private TestsessionResultDTO testsessionResultDTO;

    @BeforeEach
    void setUp() {
        testsessionResult = new TestsessionResult();
        testsessionResult.setId(1);
        testsessionResult.setTestsession(testSessionRepository.findById(1).orElse(null));

        testsessionResultDTO = new TestsessionResultDTO();
        testsessionResultDTO.setId(1);
        testsessionResultDTO.setTestsessionId(1);
    }

    @Test
    void testGetAllTestsessionResults() {
        when(testsessionResultRepository.findAll()).thenReturn(Arrays.asList(testsessionResult));
        List<TestsessionResultDTO> result = testsessionResultService.getAllTestsessionResults();
        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void testGetTestsessionResultById_Found() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.of(testsessionResult));
        ResponseEntity<TestsessionResultDTO> response = testsessionResultService.getTestsessionResultById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testGetTestsessionResultById_NotFound() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<TestsessionResultDTO> response = testsessionResultService.getTestsessionResultById(1);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testGetTestsessionResultsByTestsessionId() {
        when(testsessionResultRepository.findByTestsessionId(1)).thenReturn(Arrays.asList(testsessionResult));
        List<TestsessionResultDTO> result = testsessionResultService.getTestsessionResultsByTestsessionId(1);
        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void testCreateTestsessionResult() {
        when(testsessionResultRepository.save(any(TestsessionResult.class))).thenReturn(testsessionResult);
        TestsessionResultDTO created = testsessionResultService.createTestsessionResult(testsessionResultDTO);
        assertEquals(1, created.getId());
    }

    @Test
    void testUpdateTestsessionResult_Found() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.of(testsessionResult));
        when(testsessionResultRepository.save(any(TestsessionResult.class))).thenReturn(testsessionResult);
        ResponseEntity<TestsessionResultDTO> response = testsessionResultService.updateTestsessionResult(1, testsessionResultDTO);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testUpdateTestsessionResult_NotFound() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<TestsessionResultDTO> response = testsessionResultService.updateTestsessionResult(1, testsessionResultDTO);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTestsessionResult_Found() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.of(testsessionResult));
        doNothing().when(testsessionResultRepository).deleteById(1);
        ResponseEntity<Void> response = testsessionResultService.deleteTestsessionResult(1);
        assertEquals(204, response.getStatusCodeValue());
        verify(testsessionResultRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteTestsessionResult_NotFound() {
        when(testsessionResultRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Void> response = testsessionResultService.deleteTestsessionResult(1);
        assertEquals(404, response.getStatusCodeValue());
    }
}

