package com.example.testing.app.question;
import com.example.testing.app.model.Question;
import com.example.testing.app.repository.QuestionRepository;
import com.example.testing.app.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Test
    void testGetAllQuestions() {
        Question question = new Question();
        when(questionRepository.findAll()).thenReturn(Arrays.asList(question));

        List<Question> result = questionService.getAllQuestions();

        assertEquals(1, result.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void testGetQuestionById_Found() {
        Question question = new Question();
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        ResponseEntity<Question> result = questionService.getQuestionById(1);

        assertEquals(200, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
    }

    @Test
    void testGetQuestionById_NotFound() {
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Question> result = questionService.getQuestionById(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
    }

    @Test
    void testCreateQuestion() {
        Question question = new Question();
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        Question result = questionService.createQuestion(question);

        assertNotNull(result);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void testUpdateQuestion_Found() {
        Question question = new Question();
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        ResponseEntity<Question> result = questionService.updateQuestion(1, question);

        assertEquals(200, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void testUpdateQuestion_NotFound() {
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        Question question = new Question();
        ResponseEntity<Question> result = questionService.updateQuestion(1, question);

        assertEquals(404, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(0)).save(any(Question.class));
    }

    @Test
    void testDeleteQuestion_Found() {
        Question question = new Question();
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        ResponseEntity<Void> result = questionService.deleteQuestion(1);

        assertEquals(204, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteQuestion_NotFound() {
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = questionService.deleteQuestion(1);

        assertEquals(404, result.getStatusCodeValue());
        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(0)).deleteById(1);
    }

    @Test
    void testDeleteQuestionWithAnswers_Found() {
        Question question = new Question();
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        questionService.deleteQuestionWithAnswers(1);

        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(1)).delete(question);
    }

    @Test
    void testDeleteQuestionWithAnswers_NotFound() {
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        try {
            questionService.deleteQuestionWithAnswers(1);
        } catch (EntityNotFoundException e) {
            assertEquals("Question not found with id 1", e.getMessage());
        }

        verify(questionRepository, times(1)).findById(1);
        verify(questionRepository, times(0)).delete(any(Question.class));
    }
}

