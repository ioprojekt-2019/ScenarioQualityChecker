package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioNumberedListService;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ScenarioNumberedListServiceImplTest {
    private ScenarioNumberedListService scenarioNumberedListService;

    private final String EXAMPLE_TITLE = "HOLIDAYS";
    private final String EXAMPLE_ACTOR = "EMPLOYEE";
    private final String EXAMPLE_SYSTEM_ACTOR = "SOFTWARE";
    private final String EXAMPLE_STEP_NAME = "EMPLOYEE REQUESTS HOLIDAYS";

    @Before
    public void setUp() {
        this.scenarioNumberedListService = new ScenarioNumberedListServiceImpl();
    }


    @Test
    public void testGetScenarioAsNumberedListReturnOnlyNumberedListHeaderOnEmptyScenario() {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        String result = scenarioNumberedListService.getScenarioAsNumberedList(scenarioDTO);
        assertTrue(result.startsWith("Title:"));
        assertFalse(result.contains("1. "));
    }

    @Test
    public void testGetScenarioAsNumberedListReturnCorrectValueWithOneDepthScenario() {
        ScenarioDTO scenarioDTO = prepareOneDepthScenarioWithThreeSteps();
        String result = scenarioNumberedListService.getScenarioAsNumberedList(scenarioDTO);
        assertTrue(result.startsWith(String.format("Title: %s", EXAMPLE_TITLE)));

        assertTrue(result.contains(String.format("1. %s", EXAMPLE_STEP_NAME)));
        assertTrue(result.contains(String.format("2. %s", EXAMPLE_STEP_NAME)));
        assertTrue(result.contains(String.format("3. %s", EXAMPLE_STEP_NAME)));
        assertFalse(result.contains("\n\t1.1 "));
    }

    @Test
    public void testGetScenarioAsNumberedListReturnCorrectValueWithMixedThreeDepthScenario() {
        ScenarioDTO scenarioDTO = prepareThreeDepthsMixedScenario();
        String result = scenarioNumberedListService.getScenarioAsNumberedList(scenarioDTO);
        assertTrue(result.startsWith(String.format("Title: %s", EXAMPLE_TITLE)));

        assertTrue(result.contains(String.format("1. %s", EXAMPLE_STEP_NAME)));
        assertTrue(result.contains(String.format("\t1.1. %s", EXAMPLE_STEP_NAME)));
        assertTrue(result.contains(String.format("\t\t1.1.1. %s", EXAMPLE_STEP_NAME)));
        assertFalse(result.contains("\n\t1.2 "));
    }

    @Test
    public void testGetScenarioAsNumberedListReturnCorrectValueWithTwoDepthScenario() {
        ScenarioDTO scenarioDTO = prepareTwoDepthsScenario();
        String result = scenarioNumberedListService.getScenarioAsNumberedList(scenarioDTO);
        assertTrue(result.startsWith(String.format("Title: %s", EXAMPLE_TITLE)));

        assertTrue(result.contains(String.format("1. %s", EXAMPLE_STEP_NAME)));
        assertTrue(result.contains(String.format("\t1.1. %s", EXAMPLE_STEP_NAME)));
        assertFalse(result.contains("\n2. "));
    }

    private ScenarioDTO prepareOneDepthScenarioWithThreeSteps() {
        ScenarioDTO scenarioDTO = prepareScenario(3);
        scenarioDTO.getSteps().get(0).setName(EXAMPLE_STEP_NAME);
        scenarioDTO.getSteps().get(1).setName(EXAMPLE_STEP_NAME);
        scenarioDTO.getSteps().get(2).setName(EXAMPLE_STEP_NAME);
        return scenarioDTO;
    }

    private ScenarioDTO prepareThreeDepthsMixedScenario() {
        ScenarioDTO scenarioDTO = prepareScenario(2);
        scenarioDTO.getSteps().get(0).setName(EXAMPLE_STEP_NAME);
        scenarioDTO.getSteps().get(0).setSteps(generateScenarioSteps(1));
        scenarioDTO.getSteps().get(0).getSteps().get(0).setName(EXAMPLE_STEP_NAME);
        scenarioDTO.getSteps().get(0).getSteps().get(0).setSteps(generateScenarioSteps(1));
        scenarioDTO.getSteps().get(0).getSteps().get(0).getSteps().get(0).setName(EXAMPLE_STEP_NAME);

        scenarioDTO.getSteps().get(1).setName(EXAMPLE_STEP_NAME);

        return scenarioDTO;
    }

    private ScenarioDTO prepareTwoDepthsScenario() {
        ScenarioDTO scenarioDTO = prepareScenario(1);
        scenarioDTO.getSteps().get(0).setName(EXAMPLE_STEP_NAME);
        scenarioDTO.getSteps().get(0).setSteps(generateScenarioSteps(2));
        scenarioDTO.getSteps().get(0).getSteps().get(0).setName(EXAMPLE_STEP_NAME);

        return scenarioDTO;
    }

    private ScenarioDTO prepareScenario(int numOfSteps) {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        scenarioDTO.setTitle(EXAMPLE_TITLE);
        ArrayList<String> actors = new ArrayList<>();
        actors.add(EXAMPLE_ACTOR);
        actors.add(EXAMPLE_ACTOR);
        scenarioDTO.setActors(actors);
        scenarioDTO.setSystemActor(EXAMPLE_SYSTEM_ACTOR);
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
