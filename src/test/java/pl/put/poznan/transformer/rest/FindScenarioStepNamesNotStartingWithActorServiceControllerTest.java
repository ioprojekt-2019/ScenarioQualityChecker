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
import pl.put.poznan.transformer.logic.service.FindScenarioStepNamesNotStartingWithActorService;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FindScenarioStepNamesNotStartingWithActorServiceControllerTest {
    private FindScenarioStepNamesNotStartingWithActorServiceController controller;

    @Mock
    private FindScenarioStepNamesNotStartingWithActorService service;

    @Before
    public void setUp() {
        controller = new FindScenarioStepNamesNotStartingWithActorServiceController(service);
    }

    @Test
    public void testFindScenarioStepNamesNotStartingWithActorActionReturnsStepNamesOnSuccess() {
        mockFindScenarioLinesNotStartingWithActorServiceReturnsOneLine();
        ResponseEntity<Map<String, ArrayList<String>>> result = controller
                .getFindScenarioLinesWithoutActorInFirstWordAction(new ScenarioDTO());
        assertHaveLineAnd200HttpStatus(result);
    }

    private void mockFindScenarioLinesNotStartingWithActorServiceReturnsOneLine() {
        ArrayList<String> response = new ArrayList<>();
        response.add("SOMETHING");
        Mockito.when(service.findStepNames(Mockito.any())).thenReturn(response);
    }

    private void assertHaveLineAnd200HttpStatus(ResponseEntity<Map<String, ArrayList<String>>> response) {
        assertTrue(response.getBody().containsKey("lines"));
        assertEquals("SOMETHING", response.getBody().get("lines").get(0));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
