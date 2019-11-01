package pl.put.poznan.transformer.rest;

import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioStepsCounterService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioStepsCounterControllerTest {
    private ScenarioStepsCounterController scenarioStepsCounterController;

    @Mock
    private ScenarioStepsCounterService scenarioStepsCounterService;

    @Before
    public void setUp() {
        scenarioStepsCounterController = new ScenarioStepsCounterController(scenarioStepsCounterService);
    }

    @Test
    public void testCountScenarioStepsActionReturnsStepsCountOnSuccess() {
        mockScenarioStepsCounterServiceReturnsFive();
        ResponseEntity<Map<String, Integer>> result = scenarioStepsCounterController
                .countScenarioStepsAction(new ScenarioDTO());
        assertHaveStepsCountAnd200HttpStatus(result);
    }

    private void mockScenarioStepsCounterServiceReturnsFive() {
        Mockito.when(scenarioStepsCounterService.getCount(Mockito.any())).thenReturn(5);
    }

    private void assertHaveStepsCountAnd200HttpStatus(ResponseEntity<Map<String, Integer>> response) {
        assertTrue(response.getBody().containsKey("count"));
        assertEquals(5, response.getBody().get("count").intValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
