package pl.put.poznan.transformer.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioLinesWithoutActorInFirstWordService;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioLinesWithoutActorInFirstWordControllerTest {
    private ScenarioLinesWithoutActorInFirstWordController scenarioLinesWithoutActorInFirstWordController;

    @Mock
    private ScenarioLinesWithoutActorInFirstWordService scenarioLinesWithoutActorInFirstWordService;

    @Before
    public void setUp() {
        scenarioLinesWithoutActorInFirstWordController = new ScenarioLinesWithoutActorInFirstWordController(scenarioLinesWithoutActorInFirstWordService);
    }

    @Test
    public void testCountScenarioKeywordsActionReturnsKeywordsCountOnSuccess() {
        mock_ScenarioLinesWithoutActorInFirstWordService_ReturnsOneLine();
        ResponseEntity<Map<String, Map<String, String>>> result = scenarioLinesWithoutActorInFirstWordController
                .getScenarioLinesWithoutActorInFirstWordAction(new ScenarioDTO());
        assertHaveLineAnd200HttpStatus(result);
    }

    private void mock_ScenarioLinesWithoutActorInFirstWordService_ReturnsOneLine() {
        ArrayList<String> response = new ArrayList<>();
        response.add("SOMETHING");
        Mockito.when(scenarioLinesWithoutActorInFirstWordService.getLines(Mockito.any())).thenReturn(response);
    }

    private void assertHaveLineAnd200HttpStatus(ResponseEntity<Map<String, Map<String, String>>> response) {
        assertTrue(response.getBody().containsKey("lines"));
        assertEquals("SOMETHING", response.getBody().get("lines").get("0"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
