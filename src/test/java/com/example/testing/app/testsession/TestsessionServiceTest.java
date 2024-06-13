package com.example.testing.app.testsession;
import com.example.testing.app.dto.TestsessionDTO;
import com.example.testing.app.model.Testsession;
import com.example.testing.app.repository.TestRepository;
import com.example.testing.app.repository.TestsessionRepository;
import com.example.testing.app.service.TestsessionService;
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
public class TestsessionServiceTest {

    @InjectMocks
    private TestsessionService testsessionService;

    @Mock
    private TestsessionRepository testsessionRepository;

    @Mock
    private TestRepository testRepository;

    @Test
    void testGetAllTestSessions() {
        Testsession testSession = new Testsession();
        when(testsessionRepository.findAll()).thenReturn(Arrays.asList(testSession));

        List<TestsessionDTO> result = testsessionService.getAllTestSessions();

        assertEquals(1, result.size());
        verify(testsessionRepository, times(1)).findAll();
    }

    @Test
    void testGetTestSessionById_Found() {
        Testsession testSession = new Testsession();
        when(testsessionRepository.findById(1)).thenReturn(Optional.of(testSession));

        ResponseEntity<TestsessionDTO> result = testsessionService.getTestSessionById(1);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        verify(testsessionRepository, times(1)).findById(1);
    }

    @Test
    void testGetTestSessionById_NotFound() {
        when(testsessionRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<TestsessionDTO> result = testsessionService.getTestSessionById(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(testsessionRepository, times(1)).findById(1);
    }

    @Test
    void testCreateTestSession() {
        Testsession testSession = new Testsession();
        when(testsessionRepository.save(any(Testsession.class))).thenReturn(testSession);

        TestsessionDTO dto = new TestsessionDTO();
        TestsessionDTO result = testsessionService.createTestSession(dto);

        assertNotNull(result);
        verify(testsessionRepository, times(1)).save(any(Testsession.class));
    }

    @Test
    void testUpdateTestSession_Found() {
        Testsession testSession = new Testsession();
        when(testsessionRepository.findById(1)).thenReturn(Optional.of(testSession));
        when(testsessionRepository.save(any(Testsession.class))).thenReturn(testSession);

        TestsessionDTO dto = new TestsessionDTO();
        ResponseEntity<TestsessionDTO> result = testsessionService.updateTestSession(1, dto);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        verify(testsessionRepository, times(1)).findById(1);
        verify(testsessionRepository, times(1)).save(any(Testsession.class));
    }

    @Test
    void testUpdateTestSession_NotFound() {
        when(testsessionRepository.findById(1)).thenReturn(Optional.empty());

        TestsessionDTO dto = new TestsessionDTO();
        ResponseEntity<TestsessionDTO> result = testsessionService.updateTestSession(1, dto);

        assertEquals(404, result.getStatusCodeValue());
        verify(testsessionRepository, times(1)).findById(1);
        verify(testsessionRepository, times(0)).save(any(Testsession.class));
    }

    @Test
    void testDeleteTestSession_Found() {
        Testsession testSession = new Testsession();
        when(testsessionRepository.findById(1)).thenReturn(Optional.of(testSession));

        ResponseEntity<Void> result = testsessionService.deleteTestSession(1);

        assertEquals(204, result.getStatusCodeValue());
        verify(testsessionRepository, times(1)).findById(1);
        verify(testsessionRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteTestSession_NotFound() {
        when(testsessionRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = testsessionService.deleteTestSession(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(testsessionRepository, times(1)).findById(1);
        verify(testsessionRepository, times(0)).deleteById(1);
    }
}

