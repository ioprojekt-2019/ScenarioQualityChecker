package pl.put.poznan.transformer.rest;

import org.assertj.core.internal.cglib.asm.$Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioNumberedListService;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioNumberedListControllerTest {
    private ScenarioNumberedListController scenarioNumberedListController;

    @Mock
    private ScenarioNumberedListService scenarioNumberedListService;

    @Before
    public void setUp() {
        scenarioNumberedListController = new ScenarioNumberedListController(scenarioNumberedListService);
    }

    @Test
    public void testNumberedListActionReturnsStringWithTitleOnSuccess() {
        mockScenarioNumberedListServiceReturnsTitle();
        ResponseEntity<Map<String, String>> result = scenarioNumberedListController
                .numberedListAction(new ScenarioDTO());
        assertHaveTitleStringAnd200ResponseCode(result);
    }

    private void mockScenarioNumberedListServiceReturnsTitle() {
        Mockito.when(scenarioNumberedListService.getScenarioAsNumberedList(Mockito.any())).thenReturn("Title");
    }

    private void assertHaveTitleStringAnd200ResponseCode(ResponseEntity<Map<String, String>> response) {
        assertTrue(response.getBody().containsKey("numbered-steps"));
        assertEquals("Title", response.getBody().get("numbered-steps").toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
