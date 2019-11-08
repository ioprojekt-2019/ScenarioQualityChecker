package pl.put.poznan.transformer.logic.service;

import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

import java.util.ArrayList;

/**
 * Service used to find step names that don't start with the actor.
 */
public interface FindScenarioStepNamesNotStartingWithActorService {
    /**
     * Find lines that don't start with the actor.
     * @param scenarioDTO Object representing scenario
     * @return Step names that don't start with actor
     */
    public ArrayList<String> findStepNames(ScenarioDTO scenarioDTO);
}
