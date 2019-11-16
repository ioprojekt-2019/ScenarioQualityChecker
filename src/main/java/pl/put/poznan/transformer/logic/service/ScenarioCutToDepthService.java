package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioCutToDepthRequestDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

/**
 * Service with functionality to cut scenario with steps to given depth level
 */
public interface ScenarioCutToDepthService {
    /**
     * Returns new instance of ScenarioDTO after cutting input scenario to given depth level.
     * @param scenarioCutToDepthRequest input scenario that will be cut
     * @return instance of ScenarioDTO representing cut scenario
     */
    public ScenarioDTO cutScenarioToDepthLevel(ScenarioCutToDepthRequestDTO scenarioCutToDepthRequest);
}
