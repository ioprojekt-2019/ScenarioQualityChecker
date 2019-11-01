package pl.put.poznan.transformer.logic.domain.dto;

import java.util.ArrayList;

public class ScenarioStepDTO {
    private String name;
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
