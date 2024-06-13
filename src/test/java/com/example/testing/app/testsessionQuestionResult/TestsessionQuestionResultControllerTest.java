package com.example.testing.app.testsessionQuestionResult;
import com.example.testing.app.dto.TestsessionQuestionResultDTO;
import com.example.testing.app.service.TestsessionQuestionResultService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestsessionQuestionResultControllerTest {

    @MockBean
    private TestsessionQuestionResultService testsessionQuestionResultService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllTestsessionQuestionResults() throws Exception {
        List<TestsessionQuestionResultDTO> testsessionQuestionResultDTOList = List.of(new TestsessionQuestionResultDTO(), new TestsessionQuestionResultDTO());

        Mockito.when(testsessionQuestionResultService.getAllTestsessionQuestionResults()).thenReturn(testsessionQuestionResultDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-question-results")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTestsessionQuestionResultById_Found() throws Exception {
        TestsessionQuestionResultDTO testsessionQuestionResultDTO = new TestsessionQuestionResultDTO();
        testsessionQuestionResultDTO.setId(1);

        Mockito.when(testsessionQuestionResultService.getTestsessionQuestionResultById(1)).thenReturn(ResponseEntity.ok(testsessionQuestionResultDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTestsessionQuestionResultById_NotFound() throws Exception {
        Mockito.when(testsessionQuestionResultService.getTestsessionQuestionResultById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetQuestionResultsByTestsessionResultId() throws Exception {
        List<TestsessionQuestionResultDTO> testsessionQuestionResultDTOList = List.of(new TestsessionQuestionResultDTO(), new TestsessionQuestionResultDTO());

        Mockito.when(testsessionQuestionResultService.getQuestionResultsByTestsessionResultId(1)).thenReturn(testsessionQuestionResultDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-question-results/testsession-result/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateTestsessionQuestionResult() throws Exception {
        TestsessionQuestionResultDTO testsessionQuestionResultDTO = new TestsessionQuestionResultDTO();
        testsessionQuestionResultDTO.setId(1);

        Mockito.when(testsessionQuestionResultService.createTestsessionQuestionResult(any())).thenReturn(testsessionQuestionResultDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/testsession-question-results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionResultId\": 1, \"questionId\": 1}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTestsessionQuestionResult_Found() throws Exception {
        TestsessionQuestionResultDTO testsessionQuestionResultDTO = new TestsessionQuestionResultDTO();
        testsessionQuestionResultDTO.setId(1);

        Mockito.when(testsessionQuestionResultService.updateTestsessionQuestionResult(Mockito.eq(1), any())).thenReturn(ResponseEntity.ok(testsessionQuestionResultDTO));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionResultId\": 1, \"questionId\": 1}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTestsessionQuestionResult_NotFound() throws Exception {
        Mockito.when(testsessionQuestionResultService.updateTestsessionQuestionResult(Mockito.eq(1), any()))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionResultId\": 1, \"questionId\": 1}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTestsessionQuestionResult_Found() throws Exception {
        Mockito.when(testsessionQuestionResultService.deleteTestsessionQuestionResult(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTestsessionQuestionResult_NotFound() throws Exception {
        Mockito.when(testsessionQuestionResultService.deleteTestsessionQuestionResult(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsession-question-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

