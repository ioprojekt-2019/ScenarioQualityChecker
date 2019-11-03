package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

import java.util.ArrayList;

/**
 * Service used to find lines that don't start with the actor.
 */
public interface ScenarioLinesWithoutActorInFirstWordService {
    /**
     * Find lines that don't start with the actor.
     * @param scenarioDTO Object representing scenario
     * @return Lines that don't start with the actor
     */
    public ArrayList<String> getLines(ScenarioDTO scenarioDTO);
}
