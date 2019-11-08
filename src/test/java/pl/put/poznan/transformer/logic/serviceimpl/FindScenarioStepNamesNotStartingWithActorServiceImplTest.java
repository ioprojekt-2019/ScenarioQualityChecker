package pl.put.poznan.transformer.logic.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class FindScenarioStepNamesNotStartingWithActorServiceImplTest {

    private FindScenarioStepNamesNotStartingWithActorServiceImpl findScenarioStepNamesNotStartingWithActorService;

    private final String[] EXAMPLE_KEYWORDS = new String[]{"IF", "ELSE", "FOR EACH"};

    private final String EXAMPLE_SYSTEM_ACTOR = "SYSTEM";
    private final String EXAMPLE_ACTOR = "EMPLOYEE";
    private final String EXAMPLE_USELESS_STATEMENT = "SOMETHING";

    private final String EXAMPLE_IF_STEP_NAME = "IF " + EXAMPLE_ACTOR + " HAS SALARY > 10k";
    private final String EXAMPLE_ELSE_STEP_NAME = "ELSE " + EXAMPLE_ACTOR + " HAS SALARY <= 10k";
    private final String EXAMPLE_FOR_EACH_STEP_NAME = "FOR EACH " + EXAMPLE_ACTOR + " TASK";
    private final String EXAMPLE__STEP_NAME_KEYWORD_IN_THE_MIDDLE =  EXAMPLE_ACTOR + " SIGN THE PAPER FOR EACH INVOICE";

    @Before
    public void setUp() {
        this.findScenarioStepNamesNotStartingWithActorService = new FindScenarioStepNamesNotStartingWithActorServiceImpl(EXAMPLE_KEYWORDS);
    }

    @Test
    public void test_FindStepNames_ZeroSteps_NoKeywords_ReturnsEmptyList() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 0;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_SimpleSteps_AllStepsCorrect_NoKeywords_ReturnsEmptyList() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 0;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_SimpleSteps_SomeStepsIncorrect_NoKeywords_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_SimpleSteps_SomeStepsIncorrect_Keywords_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        addStepsWithKeywords(scenarioDTO.getSteps().get(0));
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }


    @Test
    public void test_FindStepNames_SimpleSteps_AllStepsIncorrect_NoKeywords_ReturnsAllStepsNames() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 10;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_NestedSteps_AllStepsCorrect_NoKeywords_ReturnsEmptyList() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 0, nestedCorrectSteps = 10, nestedIncorrectSteps = 0;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        addNestedSteps(scenarioDTO, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_NestedSteps_SomeStepsIncorrect_NoKeywords_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5, nestedCorrectSteps = 10, nestedIncorrectSteps = 5;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        addNestedSteps(scenarioDTO, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_NestedSteps_AllStepsIncorrect_NoKeywords_ReturnsAllStepsNames() {
        int correctSteps = 0, stepsWithoutActorAtFirstPos = 10, nestedCorrectSteps = 0, nestedIncorrectSteps = 10;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        addNestedSteps(scenarioDTO, nestedCorrectSteps, nestedIncorrectSteps);
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    @Test
    public void test_FindStepNames_NestedSteps_SomeStepsIncorrect_Keywords_ReturnsIncorrectStepsNames() {
        int correctSteps = 10, stepsWithoutActorAtFirstPos = 5, nestedCorrectSteps = 10, nestedIncorrectSteps = 5;

        ArrayList<String> stepsName = prepareStepsName(correctSteps, stepsWithoutActorAtFirstPos);
        ScenarioDTO scenarioDTO = prepareScenario(stepsName);
        addNestedSteps(scenarioDTO, nestedCorrectSteps, nestedIncorrectSteps);
        addStepsWithKeywords(scenarioDTO.getSteps().get(0));
        ArrayList<String> incorrectStepsLines = retrieveIncorrectSteps(scenarioDTO);

        ArrayList<String> lines = this.findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        assertArrayEquals(incorrectStepsLines.toArray(), lines.toArray());
    }

    private ArrayList<String> prepareStepsName(int correctSteps, int stepsWithoutActorAtFirstPos) {
        ArrayList<String> steps = new ArrayList<>();

        if(correctSteps > 0) {
            steps.add(EXAMPLE_SYSTEM_ACTOR + " " + EXAMPLE_USELESS_STATEMENT);
        }

        for(int i=0; i<correctSteps - 1; i++) {
            steps.add(EXAMPLE_ACTOR + " " + EXAMPLE_USELESS_STATEMENT);
        }

        for(int i=0; i<stepsWithoutActorAtFirstPos; i++) {
            steps.add(EXAMPLE_USELESS_STATEMENT);
        }

        return steps;
    }

    private ScenarioDTO prepareScenario(ArrayList<String> stepsName) {
        ScenarioDTO scenarioDTO = new ScenarioDTO();
        ArrayList<ScenarioStepDTO> scenarioSteps = new ArrayList<>();


        for(String sName: stepsName) {

            ScenarioStepDTO scenarioStepDTO = new ScenarioStepDTO();
            scenarioStepDTO.setName(sName);
            scenarioSteps.add(scenarioStepDTO);
        }

        ArrayList<String> actors = new ArrayList<>();
        actors.add(EXAMPLE_ACTOR);

        scenarioDTO.setActors(actors);
        scenarioDTO.setSystemActor(EXAMPLE_SYSTEM_ACTOR);
        scenarioDTO.setSteps(scenarioSteps);
        return scenarioDTO;
    }

    private void addNestedSteps(ScenarioDTO scenarioDTO, int correctSteps, int incorrectSteps) {
        ArrayList<String> stepsName = prepareStepsName(correctSteps, incorrectSteps);
        ArrayList<ScenarioStepDTO> scenarioSteps = new ArrayList<>();

        for(String sName: stepsName) {

            ScenarioStepDTO scenarioStepDTO = new ScenarioStepDTO();
            scenarioStepDTO.setName(sName);
            scenarioSteps.add(scenarioStepDTO);
        }

        scenarioDTO.getSteps().get(0).setSteps(scenarioSteps);
    }

    private void addStepsWithKeywords(ScenarioStepDTO scenarioStepDTO) {
        ArrayList<ScenarioStepDTO> steps = new ArrayList<>();
        ScenarioStepDTO s;

        s = new ScenarioStepDTO();
        s.setName(EXAMPLE_IF_STEP_NAME);
        steps.add(s);

        s = new ScenarioStepDTO();
        s.setName(EXAMPLE_ELSE_STEP_NAME);
        steps.add(s);

        s = new ScenarioStepDTO();
        s.setName(EXAMPLE_FOR_EACH_STEP_NAME);
        steps.add(s);

        s = new ScenarioStepDTO();
        s.setName(EXAMPLE__STEP_NAME_KEYWORD_IN_THE_MIDDLE);
        steps.add(s);

        scenarioStepDTO.setSteps(steps);
    }

    private ArrayList<String> retrieveIncorrectSteps(ScenarioDTO scenarioDTO) {
        ArrayList<String> cSteps = new ArrayList<>();
        Set<String> actors = new HashSet<String>();

        actors.add(EXAMPLE_ACTOR);
        actors.add(EXAMPLE_SYSTEM_ACTOR);


        Function<ScenarioStepDTO, ArrayList<String>> getIncorrectSubSteps;
        getIncorrectSubSteps = (ScenarioStepDTO subst) -> {

            ArrayList<String> substNames = new ArrayList<>();

            for(ScenarioStepDTO step: subst.getSteps()) {
                if(!actors.stream().anyMatch(actor -> Pattern.compile("^" + actor + " ").matcher(step.getName()).find())) {
                    cSteps.add(step.getName());
                }
            }

            return substNames;
        };


        for(ScenarioStepDTO step: scenarioDTO.getSteps()) {
            if(!actors.stream().anyMatch(actor -> Pattern.compile("^" + actor + " ").matcher(step.getName()).find())) {
                cSteps.add(step.getName());
            }

            cSteps.addAll(getIncorrectSubSteps.apply(step));
        }

        return cSteps;
    }

}
