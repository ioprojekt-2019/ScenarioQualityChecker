package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioCutToDepthRequestDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ScenarioCutToDepthServiceImplTest {
    private ScenarioCutToDepthServiceImpl scenarioCutToDepthService;

    @Before
    public void setUp() {
        scenarioCutToDepthService = new ScenarioCutToDepthServiceImpl();
    }

    @Test
    public void testCutScenarioToDepthLevelWorksForDepthLevelOne() {
        ScenarioCutToDepthRequestDTO inputRequest = prepareInputCutToDepthRequest(1);
        ScenarioDTO result = scenarioCutToDepthService.cutScenarioToDepthLevel(inputRequest);
        assertScenarioSameAsInputScenarioCutToDepthLevelOne(inputRequest.getScenario(), result);
    }

    @Test
    public void testCutScenarioToDepthLevelWorksForDepthLevelTwo() {
        ScenarioCutToDepthRequestDTO inputRequest = prepareInputCutToDepthRequest(2);
        ScenarioDTO result = scenarioCutToDepthService.cutScenarioToDepthLevel(inputRequest);
        assertScenarioSameAsInputScenarioCutToDepthLevelTwo(inputRequest.getScenario(), result);
    }

    @Test
    public void testCutScenarioToDepthLevelReturnsWholeScenarioWhenDepthLevelExceedsScenarioDepth() {
        ScenarioCutToDepthRequestDTO inputRequest = prepareInputCutToDepthRequest(12);
        ScenarioDTO result = scenarioCutToDepthService.cutScenarioToDepthLevel(inputRequest);
        assertScenarioSameAsInputScenario(inputRequest.getScenario(), result);
    }

    private ScenarioCutToDepthRequestDTO prepareInputCutToDepthRequest(int depthLevel) {
        return new ScenarioCutToDepthRequestDTO(depthLevel, prepareInputThreeDepthScenario());
    }

    private ScenarioDTO prepareInputThreeDepthScenario() {
        ScenarioDTO scenario = prepareScenarioWithoutSteps();
        scenario.setSteps(new ArrayList<>(Arrays.asList(
                new ScenarioStepDTO("User makes request to API"),
                new ScenarioStepDTO("IF http response code = 201", new ArrayList<>(Arrays.asList(
                        new ScenarioStepDTO("Append response body to DOM"),
                        new ScenarioStepDTO("Render page")
                ))),
                new ScenarioStepDTO("ELSE FOR EACH error", new ArrayList<>(Collections.singletonList(
                        new ScenarioStepDTO("IF fatal", new ArrayList<>(Collections.singletonList(
                                new ScenarioStepDTO("Exit and log")
                        )))
                )))
        )));

        return scenario;
    }

    private ScenarioDTO prepareScenarioWithoutSteps() {
        ScenarioDTO scenario = new ScenarioDTO();
        scenario.setTitle("Title");
        scenario.setActors(new ArrayList<>(Arrays.asList("Actor1", "Actor2")));
        scenario.setSystemActor("System");

        return scenario;
    }

    private void assertScenarioSameAsInputScenario(ScenarioDTO inputScenario, ScenarioDTO resultScenario) {
        assertScenarioSameAsInputScenarioCutToDepthLevelTwo(inputScenario, resultScenario);
        assertScenarioStepListsHaveSameStepNames(
                inputScenario.getSteps().get(2).getSteps().get(0).getSteps(),
                resultScenario.getSteps().get(2).getSteps().get(0).getSteps()
        );
    }

    private void assertScenarioSameAsInputScenarioCutToDepthLevelTwo(ScenarioDTO inputScenario, ScenarioDTO resultScenario) {
        assertScenarioSameAsInputScenarioCutToDepthLevelOne(inputScenario, resultScenario);
        for (int i : new int[]{1, 2}) {
            assertScenarioStepListsHaveSameStepNames(
                    inputScenario.getSteps().get(i).getSteps(),
                    resultScenario.getSteps().get(i).getSteps()
            );
        }
    }

    private void assertScenarioSameAsInputScenarioCutToDepthLevelOne(ScenarioDTO inputScenario, ScenarioDTO resultScenario) {
        assertEquals(inputScenario.getTitle(), resultScenario.getTitle());
        assertEquals(inputScenario.getSystemActor(), resultScenario.getSystemActor());
        assertArrayEquals(inputScenario.getActors().toArray(), resultScenario.getActors().toArray());
        assertScenarioStepListsHaveSameStepNames(inputScenario.getSteps(), resultScenario.getSteps());
    }

    private void assertScenarioStepListsHaveSameStepNames(
            ArrayList<ScenarioStepDTO> expectedSteps,
            ArrayList<ScenarioStepDTO> actualSteps) {
        assertEquals(expectedSteps.size(), actualSteps.size());
        for (int i = 0 ; i < expectedSteps.size() ; i++) {
            assertEquals(expectedSteps.get(i).getName(), actualSteps.get(i).getName());
        }
    }
}
