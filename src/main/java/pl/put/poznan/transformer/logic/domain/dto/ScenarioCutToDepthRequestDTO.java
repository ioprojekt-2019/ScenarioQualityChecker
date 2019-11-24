package pl.put.poznan.transformer.logic.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ScenarioCutToDepthRequestDTO {
    @Min(value = 1, message = "Depth level should be at least 1")
    private int cutDepthLevel;

    @Valid
    @NotNull(message = "Scenario can't be empty")
    private ScenarioDTO scenario;

    public ScenarioCutToDepthRequestDTO() {
    }

    public ScenarioCutToDepthRequestDTO(int cutDepthLevel, ScenarioDTO scenario) {
        this.cutDepthLevel = cutDepthLevel;
        this.scenario = scenario;
    }

    public int getCutDepthLevel() {
        return cutDepthLevel;
    }

    public void setCutDepthLevel(int cutDepthLevel) {
        this.cutDepthLevel = cutDepthLevel;
    }

    public ScenarioDTO getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioDTO scenario) {
        this.scenario = scenario;
    }
}
