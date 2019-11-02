package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

/**
 * Service for counting the number of keywords occurring at the beginning of all steps in a scenario
 */
public interface ScenarioKeywordsCounterService {
    /**
     * Calculates the number of keywords in a scenario
     * @param scenarioDTO scenario of which the number of keywords will be calculated
     * @return number of keywords in a given scenario
     */
    int getNumberOfKeywords(ScenarioDTO scenarioDTO);
}
