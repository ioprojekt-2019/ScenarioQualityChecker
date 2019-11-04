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
import pl.put.poznan.transformer.logic.service.ScenarioKeywordsCounterService;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioKeywordsCounterControllerTest {
    private ScenarioKeywordsCounterController scenarioKeywordsCounterController;

    @Mock
    private ScenarioKeywordsCounterService scenarioKeywordsCounterService;

    @Before
    public void setUp() {
        scenarioKeywordsCounterController = new ScenarioKeywordsCounterController(scenarioKeywordsCounterService);
    }

    @Test
    public void testCountScenarioKeywordsActionReturnsKeywordsCountOnSuccess() {
        mockScenarioKeywordsCounterServiceReturnsFive();
        ResponseEntity<Map<String, Integer>> result = scenarioKeywordsCounterController
                .countScenarioKeywordsAction(new ScenarioDTO());
        assertHaveStepsCountAnd200HttpStatus(result);
    }

    private void mockScenarioKeywordsCounterServiceReturnsFive() {
        Mockito.when(scenarioKeywordsCounterService.getNumberOfKeywords(Mockito.any())).thenReturn(5);
    }

    private void assertHaveStepsCountAnd200HttpStatus(ResponseEntity<Map<String, Integer>> response) {
        assertTrue(response.getBody().containsKey("count"));
        assertEquals(5, response.getBody().get("count").intValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
