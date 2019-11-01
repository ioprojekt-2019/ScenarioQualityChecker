package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import static org.junit.Assert.assertEquals;

public class ScenarioStepsCounterServiceImplTest {
    private ScenarioStepsCounterServiceImpl scenarioStepsCounterService;

    @Before
    public void setUp() {
        this.scenarioStepsCounterService = new ScenarioStepsCounterServiceImpl();
    }

    @Test
    public void testGetCountReturnsZeroForEmptyScenario() {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        int result = scenarioStepsCounterService.getCount(scenarioDTO);
        assertEquals(0, result);
    }

    @Test
    public void testGetCountWorksForOneDepthScenarioWithThreeSteps() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithThreeSteps();
        int result = scenarioStepsCounterService.getCount(scenarioDTO);
        assertEquals(3, result);
    }

    @Test
    public void testGetCountWorksForScenarioWithThreeStepsAndOneSubscenario() {
        ScenarioDTO scenarioDTO = prepareScenarioWithThreeStepsAndOneSubscenario();
        int result = scenarioStepsCounterService.getCount(scenarioDTO);
        assertEquals(5, result);
    }

    private ScenarioDTO prepareScenarioWithThreeStepsAndOneSubscenario() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithThreeSteps();
        scenarioDTO.getSteps().get(0).setSteps(generateScenarioSteps(2));

        return scenarioDTO;
    }

    private ScenarioDTO prepareOneDepthScenarioWithThreeSteps() {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        scenarioDTO.setSteps(generateScenarioSteps(3));

        return scenarioDTO;
    }

    private ArrayList<ScenarioStepDTO> generateScenarioSteps(int numSteps) {
        ArrayList<ScenarioStepDTO> steps = new ArrayList<>();
        for (int i = 0 ; i < numSteps ; i++) {
            steps.add(new ScenarioStepDTO());
        }

        return steps;
    }
}
