package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

/**
 * Service used for counting steps of scenario and all its sub scenarios. Available as bean.
 */
public interface ScenarioStepsCounterService {
    /**
     * Calculates number of steps for given scenario
     * @param scenarioDTO Object representing scenario of which number of steps should be returned
     * @return Number of steps of scenario passed as method argument
     */
    public int getCount(ScenarioDTO scenarioDTO);
}
