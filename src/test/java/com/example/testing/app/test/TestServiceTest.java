package com.example.testing.app.test;
import com.example.testing.app.model.Test;
import com.example.testing.app.repository.TestRepository;
import com.example.testing.app.service.TestService;
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
public class TestServiceTest {

    @InjectMocks
    private TestService testService;

    @Mock
    private TestRepository testRepository;

    @org.junit.jupiter.api.Test
    void testGetAllTests() {
        Test test = new Test();
        when(testRepository.findAll()).thenReturn(Arrays.asList(test));

        List<Test> result = testService.getAllTests();

        assertEquals(1, result.size());
        verify(testRepository, times(1)).findAll();
    }

    @org.junit.jupiter.api.Test
    void testGetTestById_Found() {
        Test test = new Test();
        when(testRepository.findById(1)).thenReturn(Optional.of(test));

        ResponseEntity<Test> result = testService.getTestById(1);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        verify(testRepository, times(1)).findById(1);
    }

    @org.junit.jupiter.api.Test
    void testGetTestById_NotFound() {
        when(testRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Test> result = testService.getTestById(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(testRepository, times(1)).findById(1);
    }

    @org.junit.jupiter.api.Test
    void testCreateTest() {
        Test test = new Test();
        when(testRepository.save(any(Test.class))).thenReturn(test);

        Test result = testService.createTest(test);

        assertNotNull(result);
        verify(testRepository, times(1)).save(any(Test.class));
    }

    @org.junit.jupiter.api.Test
    void testUpdateTest_Found() {
        Test test = new Test();
        when(testRepository.findById(1)).thenReturn(Optional.of(test));
        when(testRepository.save(any(Test.class))).thenReturn(test);

        ResponseEntity<Test> result = testService.updateTest(1, test);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        verify(testRepository, times(1)).findById(1);
        verify(testRepository, times(1)).save(any(Test.class));
    }

    @org.junit.jupiter.api.Test
    void testUpdateTest_NotFound() {
        when(testRepository.findById(1)).thenReturn(Optional.empty());

        Test test = new Test();
        ResponseEntity<Test> result = testService.updateTest(1, test);

        assertEquals(404, result.getStatusCodeValue());
        verify(testRepository, times(1)).findById(1);
        verify(testRepository, times(0)).save(any(Test.class));
    }

    @org.junit.jupiter.api.Test
    void testDeleteTest_Found() {
        Test test = new Test();
        when(testRepository.findById(1)).thenReturn(Optional.of(test));

        ResponseEntity<Void> result = testService.deleteTest(1);

        assertEquals(204, result.getStatusCodeValue());
        verify(testRepository, times(1)).findById(1);
        verify(testRepository, times(1)).deleteById(1);
    }

    @org.junit.jupiter.api.Test
    void testDeleteTest_NotFound() {
        when(testRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = testService.deleteTest(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(testRepository, times(1)).findById(1);
        verify(testRepository, times(0)).deleteById(1);
    }
}

