package pl.put.poznan.transformer.logic.domain.dto;

import java.util.ArrayList;

public class ScenarioDTO {
    private String title;
    private String systemActor;
    private ArrayList<String> actors;
    private ArrayList<ScenarioStepDTO> steps = new ArrayList<>();

    public ArrayList<ScenarioStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<ScenarioStepDTO> steps) {
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSystemActor() {
        return systemActor;
    }

    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }
}
