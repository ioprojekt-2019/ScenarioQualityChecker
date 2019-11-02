package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ScenarioKeywordsCounterServiceImplTest {
    private ScenarioKeywordsCounterServiceImpl scenarioKeywordsCounterService;

    private final String EXAMPLE_IF_STEP_NAME = "IF EMPLOYEE HAS SALARY > 10k";
    private final String EXAMPLE_ELSE_STEP_NAME = "ELSE EMPLOYEE HAS SALARY <= 10k";
    private final String EXAMPLE_FOR_EACH_STEP_NAME = "FOR EACH EMPLOYEE TASK";
    private final String EXAMPLE__STEP_NAME_WITHOUT_KEYWORDS = "EMPLOYEE REQUESTS HOLIDAYS";
    private final String EXAMPLE__STEP_NAME_KEYWORD_IN_THE_MIDDLE = "EMPLOYEE SIGN THE PAPER FOR EACH INVOICE";

    @Before
    public void setUp() {
        this.scenarioKeywordsCounterService = new ScenarioKeywordsCounterServiceImpl(
                new String[]{"IF", "ELSE", "FOR EACH"});
    }

    @Test
    public void testGetNumberOfKeywordsReturnZeroOnEmptyScenario() {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        int result = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        assertEquals(0, result);
    }

    @Test
    public void testGetNumberOfKeywordsReturnCorrectValueWithOneDepthScenario() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithTwoKeywords();
        int result = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        assertEquals(2, result);
    }

    @Test
    public void testGetNumberOfKeywordsWhenScenarioContainsStepsWithoutKeywords() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithTwoKeywords();
        scenarioDTO.getSteps().get(2).setName(EXAMPLE__STEP_NAME_WITHOUT_KEYWORDS);
        int result = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        assertEquals(2, result);
    }

    @Test
    public void testGetNumberOfKeywordsWhenScenarioContainsStepsWithKeywordsNotAtTheBeginning() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithTwoKeywords();
        scenarioDTO.getSteps().get(2).setName(EXAMPLE__STEP_NAME_WITHOUT_KEYWORDS);
        scenarioDTO.getSteps().get(3).setName(EXAMPLE__STEP_NAME_KEYWORD_IN_THE_MIDDLE);
        int result = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        assertEquals(2, result);
    }

    @Test
    public void testGetNumberOfKeywordsReturnCorrectValueWithTwoDepthScenario() {
        ScenarioDTO scenarioDTO = prepareScenarioWithThreeKeywordsOnTwoDepths();
        int result = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        assertEquals(3, result);
    }

    private ScenarioDTO prepareOneDepthScenarioWithTwoKeywords() {
        ScenarioDTO scenarioDTO = prepareScenario(5);
        scenarioDTO.getSteps().get(0).setName(EXAMPLE_IF_STEP_NAME);
        scenarioDTO.getSteps().get(1).setName(EXAMPLE_FOR_EACH_STEP_NAME);

        return scenarioDTO;
    }

    private ScenarioDTO prepareScenarioWithThreeKeywordsOnTwoDepths() {
        ScenarioDTO scenarioDTO = prepareScenario(4);
        scenarioDTO.getSteps().get(0).setName(EXAMPLE_IF_STEP_NAME);
        scenarioDTO.getSteps().get(0).setSteps(generateScenarioSteps(3));
        scenarioDTO.getSteps().get(0).getSteps().get(0).setName(EXAMPLE_ELSE_STEP_NAME);
        scenarioDTO.getSteps().get(1).setName(EXAMPLE_FOR_EACH_STEP_NAME);

        return scenarioDTO;
    }

    private ScenarioDTO prepareScenario(int numOfSteps) {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        scenarioDTO.setSteps(generateScenarioSteps(numOfSteps));

        return scenarioDTO;
    }

    private ArrayList<ScenarioStepDTO> generateScenarioSteps(int numSteps) {
        ArrayList<ScenarioStepDTO> steps = new ArrayList<>();
        for (int i = 0; i < numSteps; i++) {
            steps.add(new ScenarioStepDTO());
        }

        return steps;
    }
}
