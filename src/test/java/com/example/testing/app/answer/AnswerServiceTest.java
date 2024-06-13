package com.example.testing.app.answer;

import com.example.testing.app.model.Answer;
import com.example.testing.app.repository.AnswerRepository;
import com.example.testing.app.service.AnswerService;
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
public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer();
        answer.setId(1);
        answer.setAnswerText("Sample Answer");
    }

    @Test
    void testGetAllAnswers() {
        when(answerRepository.findAll()).thenReturn(Arrays.asList(answer));
        List<Answer> answers = answerService.getAllAnswers();
        assertFalse(answers.isEmpty());
        assertEquals(1, answers.get(0).getId());
    }

    @Test
    void testGetAnswerById_Found() {
        when(answerRepository.findById(1)).thenReturn(Optional.of(answer));
        ResponseEntity<Answer> response = answerService.getAnswerById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testGetAnswerById_NotFound() {
        when(answerRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Answer> response = answerService.getAnswerById(1);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateAnswer() {
        when(answerRepository.save(answer)).thenReturn(answer);
        Answer createdAnswer = answerService.createAnswer(answer);
        assertEquals(1, createdAnswer.getId());
        assertEquals("Sample Answer", createdAnswer.getAnswerText());
    }

    @Test
    void testUpdateAnswer_Found() {
        when(answerRepository.findById(1)).thenReturn(Optional.of(answer));
        when(answerRepository.save(answer)).thenReturn(answer);
        ResponseEntity<Answer> response = answerService.updateAnswer(1, answer);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testUpdateAnswer_NotFound() {
        when(answerRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Answer> response = answerService.updateAnswer(1, answer);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteAnswer_Found() {
        when(answerRepository.findById(1)).thenReturn(Optional.of(answer));
        doNothing().when(answerRepository).deleteById(1);
        ResponseEntity<Void> response = answerService.deleteAnswer(1);
        assertEquals(204, response.getStatusCodeValue());
        verify(answerRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAnswer_NotFound() {
        when(answerRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Void> response = answerService.deleteAnswer(1);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testGetAnswersByQuestionId() {
        when(answerRepository.findByQuestionId(1)).thenReturn(Arrays.asList(answer));
        List<Answer> answers = answerService.getAnswersByQuestionId(1);
        assertFalse(answers.isEmpty());
        assertEquals(1, answers.get(0).getId());
    }

    @Test
    void testDeleteAnswersByQuestionId() {
        doNothing().when(answerRepository).deleteByQuestionId(1);
        answerService.deleteAnswersByQuestionId(1);
        verify(answerRepository, times(1)).deleteByQuestionId(1);
    }
}

