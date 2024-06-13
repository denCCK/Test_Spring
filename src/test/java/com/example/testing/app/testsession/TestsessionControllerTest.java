package com.example.testing.app.testsession;
import com.example.testing.app.controller.TestsessionController;
import com.example.testing.app.dto.TestsessionDTO;
import com.example.testing.app.service.TestsessionService;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TestsessionController.class)
public class TestsessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestsessionService testsessionService;

    @Test
    void testGetAllTestsessions() throws Exception {
        TestsessionDTO testSessionDTO = new TestsessionDTO();
        Mockito.when(testsessionService.getAllTestSessions()).thenReturn(Arrays.asList(testSessionDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsessions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetTestsessionById_Found() throws Exception {
        TestsessionDTO testSessionDTO = new TestsessionDTO();
        Mockito.when(testsessionService.getTestSessionById(1)).thenReturn(ResponseEntity.ok(testSessionDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetTestsessionById_NotFound() throws Exception {
        Mockito.when(testsessionService.getTestSessionById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTestsession() throws Exception {
        TestsessionDTO testSessionDTO = new TestsessionDTO();
        Mockito.when(testsessionService.createTestSession(Mockito.any(TestsessionDTO.class)))
                .thenReturn(testSessionDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/testsessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionName\": \"Test Session\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateTestsession_Found() throws Exception {
        TestsessionDTO testSessionDTO = new TestsessionDTO();
        Mockito.when(testsessionService.updateTestSession(Mockito.eq(1), Mockito.any(TestsessionDTO.class)))
                .thenReturn(ResponseEntity.ok(testSessionDTO));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionName\": \"Updated Test Session\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testUpdateTestsession_NotFound() throws Exception {
        Mockito.when(testsessionService.updateTestSession(Mockito.eq(1), Mockito.any(TestsessionDTO.class)))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testsessionName\": \"Updated Test Session\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTestsession_Found() throws Exception {
        Mockito.when(testsessionService.deleteTestSession(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTestsession_NotFound() throws Exception {
        Mockito.when(testsessionService.deleteTestSession(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/testsessions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
