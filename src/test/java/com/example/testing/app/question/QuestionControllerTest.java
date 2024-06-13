package com.example.testing.app.question;
import com.example.testing.app.model.Question;
import com.example.testing.app.service.QuestionService;
import com.example.testing.app.controller.QuestionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    void testGetAllQuestions() throws Exception {
        Question question = new Question();
        Mockito.when(questionService.getAllQuestions()).thenReturn(Arrays.asList(question));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetQuestionById_Found() throws Exception {
        Question question = new Question();
        question.setId(1);
        Mockito.when(questionService.getQuestionById(1)).thenReturn(ResponseEntity.ok(question));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetQuestionById_NotFound() throws Exception {
        Mockito.when(questionService.getQuestionById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateQuestion() throws Exception {
        Question question = new Question();
        question.setId(1);
        Mockito.when(questionService.createQuestion(Mockito.any(Question.class))).thenReturn(question);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionText\": \"Sample Question\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateQuestion_Found() throws Exception {
        Question question = new Question();
        question.setId(1);
        Mockito.when(questionService.updateQuestion(Mockito.eq(1), Mockito.any(Question.class)))
                .thenReturn(ResponseEntity.ok(question));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionText\": \"Updated Question Text\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateQuestion_NotFound() throws Exception {
        Mockito.when(questionService.updateQuestion(Mockito.eq(1), Mockito.any(Question.class)))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionText\": \"Updated Question Text\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteQuestion_Found() throws Exception {
        Mockito.when(questionService.deleteQuestion(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteQuestion_NotFound() throws Exception {
        Mockito.when(questionService.deleteQuestion(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/questions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteQuestionWithAnswers_Found() throws Exception {
        Mockito.doNothing().when(questionService).deleteQuestionWithAnswers(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/questions/answers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

