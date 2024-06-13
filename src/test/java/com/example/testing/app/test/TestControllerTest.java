package com.example.testing.app.test;
import com.example.testing.app.controller.TestController;
import com.example.testing.app.model.Test;
import com.example.testing.app.service.TestService;
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
@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @org.junit.jupiter.api.Test
    void testGetAllTests() throws Exception {
        Test test = new Test();
        Mockito.when(testService.getAllTests()).thenReturn(Arrays.asList(test));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @org.junit.jupiter.api.Test
    void testGetTestById_Found() throws Exception {
        Test test = new Test();
        Mockito.when(testService.getTestById(1)).thenReturn(ResponseEntity.ok(test));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @org.junit.jupiter.api.Test
    void testGetTestById_NotFound() throws Exception {
        Mockito.when(testService.getTestById(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Test
    void testCreateTest() throws Exception {
        Test test = new Test();
        Mockito.when(testService.createTest(Mockito.any(Test.class))).thenReturn(test);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testName\": \"Test Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @org.junit.jupiter.api.Test
    void testUpdateTest_Found() throws Exception {
        Test test = new Test();
        Mockito.when(testService.updateTest(Mockito.eq(1), Mockito.any(Test.class)))
                .thenReturn(ResponseEntity.ok(test));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testName\": \"Updated Test Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @org.junit.jupiter.api.Test
    void testUpdateTest_NotFound() throws Exception {
        Mockito.when(testService.updateTest(Mockito.eq(1), Mockito.any(Test.class)))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testName\": \"Updated Test Name\"}"))
                .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Test
    void testDeleteTest_Found() throws Exception {
        Mockito.when(testService.deleteTest(1)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @org.junit.jupiter.api.Test
    void testDeleteTest_NotFound() throws Exception {
        Mockito.when(testService.deleteTest(1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

