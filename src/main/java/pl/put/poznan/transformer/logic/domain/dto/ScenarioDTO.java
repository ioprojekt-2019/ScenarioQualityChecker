package pl.put.poznan.transformer.logic.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;
import pl.put.poznan.transformer.logic.validator.ListOfNonemptyStrings;

import javax.validation.Valid;
import java.util.ArrayList;

public class ScenarioDTO {
    @NotEmpty(message = "Title can't be empty")
    private String title;

    @NotEmpty(message = "System actor can't be empty")
    private String systemActor;

    @ListOfNonemptyStrings(message = "Each actor must be a nonempty string")
    private ArrayList<String> actors = new ArrayList<>();

    @Valid
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
