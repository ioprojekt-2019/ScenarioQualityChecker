package pl.put.poznan.transformer.logic.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.ArrayList;

public class ScenarioStepDTO {
    @NotEmpty(message = "Name of a step can't be empty")
    private String name;

    @Valid
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<ScenarioStepDTO> steps = new ArrayList<>();

    public ScenarioStepDTO() {
    }

    public ScenarioStepDTO(String name) {
        setName(name);
    }

    public ScenarioStepDTO(String name, ArrayList<ScenarioStepDTO> steps) {
        setName(name);
        setSteps(steps);
    }

    public ScenarioStepDTO(ScenarioStepDTO otherScenarioStep) {
        setName(otherScenarioStep.getName());
        for (ScenarioStepDTO scenarioStep : otherScenarioStep.getSteps()) {
            steps.add(new ScenarioStepDTO(scenarioStep));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ScenarioStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<ScenarioStepDTO> steps) {
        this.steps = steps;
    }
}
