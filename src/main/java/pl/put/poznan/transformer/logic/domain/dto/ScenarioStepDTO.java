package pl.put.poznan.transformer.logic.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.ArrayList;

public class ScenarioStepDTO {
    @NotEmpty(message = "Name of a step can't be empty")
    private String name;

    @Valid
    private ArrayList<ScenarioStepDTO> steps = new ArrayList<>();

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
