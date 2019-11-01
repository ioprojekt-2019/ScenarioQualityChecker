package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

public interface ScenarioStepsCounterService {
    public int getCount(ScenarioDTO scenarioDTO);
}
