package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;

import java.util.ArrayList;
import java.util.function.Function;

import static org.junit.Assert.*;

public class ScenarioLinesWithoutActorInFirstWordServiceImplTest {

    private ScenarioLinesWithoutActorInFirstWordServiceImpl scenarioLinesWithoutActorInFirstWordService;

    @Before
    public void setUp() {
        this.scenarioLinesWithoutActorInFirstWordService = new ScenarioLinesWithoutActorInFirstWordServiceImpl();
    }

    @Test
    public void test_GetLines_ZeroSteps_ReturnsEmptyList() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 0;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareSimpleScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_SimpleSteps_AllStepsCorrect_ReturnsEmptyList() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 0;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareSimpleScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_SimpleSteps_SomeStepsIncorrect_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareSimpleScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_SimpleSteps_AllStepsIncorrect_ReturnsAllStepsNames() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 10;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareSimpleScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_NestedSteps_AllStepsCorrect_ReturnsEmptyList() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 0, nestedCorrectSteps = 10, nestedIncorrectSteps = 0;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareNestedStepsScenario(stepsName, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_NestedSteps_SomeStepsIncorrect_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5, nestedCorrectSteps = 10, nestedIncorrectSteps = 5;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareNestedStepsScenario(stepsName, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_GetLines_NestedSteps_AllStepsIncorrect_ReturnsAllStepsNames() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 10, nestedCorrectSteps = 0, nestedIncorrectSteps = 10;

        ArrayList<String> stepsName = prepareSimpleSteps(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareNestedStepsScenario(stepsName, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    private ArrayList<String> retrieveIncorrectSteps(ScenarioDTO scenarioDTO) {
        ArrayList<String> cSteps = new ArrayList<>();


        Function<ScenarioStepDTO, ArrayList<String>> getIncorrectSubSteps;
        getIncorrectSubSteps = (ScenarioStepDTO substep) -> {

            ArrayList<String> substepNames = new ArrayList<>();

            for(ScenarioStepDTO step: substep.getSteps()) {
                String[] words = step.getName().split(" ");

                if(words[0].equals("COMMAND")) {
                    substepNames.add(step.getName());
                }
            }

            return substepNames;
        };


        for(ScenarioStepDTO step: scenarioDTO.getSteps()) {
            String[] words = step.getName().split(" ");

            if(words[0].equals("COMMAND")) {
                cSteps.add(step.getName());
            }

            cSteps.addAll(getIncorrectSubSteps.apply(step));
        }

        return cSteps;
    }

    private ScenarioDTO prepareSimpleScenario(ArrayList<String> stepsName) {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        ArrayList<ScenarioStepDTO> scenarioSteps = new ArrayList<>();


        for(String sName: stepsName) {

            ScenarioStepDTO scenarioStepDTO = new ScenarioStepDTO();
            scenarioStepDTO.setName(sName);
            scenarioSteps.add(scenarioStepDTO);
        }

        ArrayList<String> actors = new ArrayList<>();
        actors.add("ACTOR");

        scenarioDTO.setActors(actors);
        scenarioDTO.setSystemActor("SYSTEM");
        scenarioDTO.setSteps(scenarioSteps);
        return scenarioDTO;
    }

    private ScenarioDTO prepareNestedStepsScenario(ArrayList<String> stepsName, int nestedCorrectSteps, int nestedIncorrectSteps) {
        ScenarioDTO scenarioDTO = prepareSimpleScenario(stepsName);
        ArrayList<ScenarioStepDTO> steps = new ArrayList<>();

        for(int i=0; i<nestedCorrectSteps; i++) {
            ScenarioStepDTO scenarioStepDTO = new ScenarioStepDTO();
            scenarioStepDTO.setName("ACTOR COMMAND");
            steps.add(scenarioStepDTO);
        }

        for(int i=0; i<nestedIncorrectSteps; i++) {
            ScenarioStepDTO scenarioStepDTO = new ScenarioStepDTO();
            scenarioStepDTO.setName("COMMAND");
            steps.add(scenarioStepDTO);
        }

        scenarioDTO.getSteps().get(0).setSteps(steps);

        return scenarioDTO;
    }

    private ArrayList<String> prepareSimpleSteps(int correctSteps, int stepsWithoutActorAtFirstPos) {

        ArrayList<String> steps = new ArrayList<>();

        if(correctSteps > 0) {
            steps.add("SYSTEM COMMAND");
        }

        for(int i=0; i<correctSteps - 1; i++) {
            steps.add("ACTOR COMMAND");
        }

        for(int i=0; i<stepsWithoutActorAtFirstPos; i++) {
            steps.add("COMMAND");
        }

        return steps;
    }
}
