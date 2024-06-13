package com.example.testing.app.answer;

import com.example.testing.app.controller.AnswerController;
import com.example.testing.app.model.Answer;
import com.example.testing.app.service.AnswerService;
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
@WebMvcTest(AnswerController.class)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @Test
    void testGetAllAnswers() throws Exception {
        Answer answer = new Answer();
        answer.setId(1);
        Mockito.when(answerService.getAllAnswers()).thenReturn(Arrays.asList(answer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetAnswerById_Found() throws Exception {
        Answer answer = new Answer();
        answer.setId(1);
        Mockito.when(answerService.getAnswerById(1)).thenReturn(ResponseEntity.ok(answer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAnswerById_NotFound() throws Exception {
        Mockito.when(answerService.getAnswerById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAnswer() throws Exception {
        Answer answer = new Answer();
        answer.setId(1);
        Mockito.when(answerService.createAnswer(Mockito.any(Answer.class))).thenReturn(answer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"answerText\": \"Sample Answer\", \"questionId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateAnswer_Found() throws Exception {
        Answer answer = new Answer();
        answer.setId(1);
        Mockito.when(answerService.updateAnswer(Mockito.eq(1), Mockito.any(Answer.class)))
                .thenReturn(ResponseEntity.ok(answer));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"answerText\": \"Updated Answer Text\", \"questionId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateAnswer_NotFound() throws Exception {
        Mockito.when(answerService.updateAnswer(Mockito.eq(1), Mockito.any(Answer.class)))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"answerText\": \"Updated Answer Text\", \"questionId\": 1}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAnswer_Found() throws Exception {
        Mockito.when(answerService.deleteAnswer(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAnswer_NotFound() throws Exception {
        Mockito.when(answerService.deleteAnswer(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/answers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAnswersByQuestionId() throws Exception {
        Answer answer = new Answer();
        answer.setId(1);
        Mockito.when(answerService.getAnswersByQuestionId(1)).thenReturn(Arrays.asList(answer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers/question/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testDeleteAnswersByQuestionId() throws Exception {
        Mockito.doNothing().when(answerService).deleteAnswersByQuestionId(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/answers/question/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

