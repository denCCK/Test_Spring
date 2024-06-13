package com.example.testing.app.testsessionAnswerResult;
import com.example.testing.app.controller.TestsessionAnswerResultController;
import com.example.testing.app.dto.TestsessionAnswerResultDTO;
import com.example.testing.app.service.TestsessionAnswerResultService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestsessionAnswerResultControllerTest {

    @MockBean
    private TestsessionAnswerResultService answerResultService;

    @Test
    void testGetAllAnswerResults() {
        List<TestsessionAnswerResultDTO> expectedDTOs = List.of(new TestsessionAnswerResultDTO(), new TestsessionAnswerResultDTO());

        Mockito.when(answerResultService.getAllAnswersResult()).thenReturn(expectedDTOs);

        TestsessionAnswerResultController answerResultController = new TestsessionAnswerResultController(answerResultService);
        List<TestsessionAnswerResultDTO> actualDTOs = answerResultController.getAllAnswerResults();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        assertEquals(expectedDTOs, actualDTOs);
    }

    @Test
    void testGetAnswerResultById_Found() {
        TestsessionAnswerResultDTO expectedDTO = new TestsessionAnswerResultDTO();
        expectedDTO.setId(1);

        Mockito.when(answerResultService.getAnswerResultById(1)).thenReturn(new ResponseEntity<>(expectedDTO, HttpStatus.OK));

        TestsessionAnswerResultController answerResultController = new TestsessionAnswerResultController(answerResultService);
        ResponseEntity<TestsessionAnswerResultDTO> responseEntity = answerResultController.getAnswerResultById(1);

        assertEquals(expectedDTO, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAnswerResultById_NotFound() {
        Mockito.when(answerResultService.getAnswerResultById(1)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        TestsessionAnswerResultController answerResultController = new TestsessionAnswerResultController(answerResultService);
        ResponseEntity<TestsessionAnswerResultDTO> responseEntity = answerResultController.getAnswerResultById(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
