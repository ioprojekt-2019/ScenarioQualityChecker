package pl.put.poznan.transformer.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioCutToDepthRequestDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioCutToDepthService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioCutToDepthControllerTest {
    private ScenarioCutToDepthController scenarioCutToDepthController;

    @Mock
    private ScenarioCutToDepthService scenarioCutToDepthService;

    @Before
    public void setUp() {
        scenarioCutToDepthController = new ScenarioCutToDepthController(scenarioCutToDepthService);
    }

    @Test
    public void testCutScenarioToDepthActionReturnsScenarioAnd200HttpStatusOnSuccess() {
        ScenarioCutToDepthRequestDTO input = new ScenarioCutToDepthRequestDTO(1, new ScenarioDTO());
        mockScenarioCutToDepthServiceReturnsScenarioDTO();
        ResponseEntity<ScenarioDTO> result = scenarioCutToDepthController.cutScenarioToDepthAction(input);
        assertResponseHas200HttpStatusAndScenario(result);
    }

    private void mockScenarioCutToDepthServiceReturnsScenarioDTO() {
        Mockito.doReturn(new ScenarioDTO()).when(scenarioCutToDepthService).cutScenarioToDepthLevel(Mockito.any());
    }

    private void assertResponseHas200HttpStatusAndScenario(ResponseEntity<ScenarioDTO> response) {
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
