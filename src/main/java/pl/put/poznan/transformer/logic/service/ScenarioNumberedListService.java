package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

/**
 * Service for transforming the scenario into a numbered list of steps
 */
public interface ScenarioNumberedListService {
    /**
     * Transforms ScenarioDTO into numbered list string
     * @param scenarioDTO scenario of which the list will be created
     * @return numbered list as a string
     */
    String getScenarioAsNumberedList(ScenarioDTO scenarioDTO);
}
