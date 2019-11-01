package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

public interface ScenarioKeywordsCounterService {
    int getNumberOfKeywords(ScenarioDTO scenarioDTO);
}
