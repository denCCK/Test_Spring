package com.example.testing.app.testsessionResult;

import com.example.testing.app.controller.TestsessionResultController;
import com.example.testing.app.dto.TestsessionResultDTO;
import com.example.testing.app.service.TestsessionResultService;
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
@WebMvcTest(TestsessionResultController.class)
public class TestsessionResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestsessionResultService testsessionResultService;

    @Test
    void testGetAllTestsessionResults() throws Exception {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setId(1);
        Mockito.when(testsessionResultService.getAllTestsessionResults()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-results")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetTestsessionResultById_Found() throws Exception {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setId(1);
        Mockito.when(testsessionResultService.getTestsessionResultById(1)).thenReturn(ResponseEntity.ok(dto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetTestsessionResultById_NotFound() throws Exception {
        Mockito.when(testsessionResultService.getTestsessionResultById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTestsessionResultsByTestsessionId() throws Exception {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setId(1);
        Mockito.when(testsessionResultService.getTestsessionResultsByTestsessionId(1)).thenReturn(Arrays.asList(dto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsession-results/testsession/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testCreateTestsessionResult() throws Exception {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setId(1);
        Mockito.when(testsessionResultService.createTestsessionResult(Mockito.any(TestsessionResultDTO.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/testsession-results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateTestsessionResult_Found() throws Exception {
        TestsessionResultDTO dto = new TestsessionResultDTO();
        dto.setId(1);
        Mockito.when(testsessionResultService.updateTestsessionResult(Mockito.eq(1), Mockito.any(TestsessionResultDTO.class)))
                .thenReturn(ResponseEntity.ok(dto));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateTestsessionResult_NotFound() throws Exception {
        Mockito.when(testsessionResultService.updateTestsessionResult(Mockito.eq(1), Mockito.any(TestsessionResultDTO.class)))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionId\": 1}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTestsessionResult_Found() throws Exception {
        Mockito.when(testsessionResultService.deleteTestsessionResult(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTestsessionResult_NotFound() throws Exception {
        Mockito.when(testsessionResultService.deleteTestsessionResult(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsession-results/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

