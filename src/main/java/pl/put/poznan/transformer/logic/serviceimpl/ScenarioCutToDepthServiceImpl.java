package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioCutToDepthRequestDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioCutToDepthService;

import java.util.ArrayList;

@Service
public class ScenarioCutToDepthServiceImpl implements ScenarioCutToDepthService {
    @Override
    public ScenarioDTO cutScenarioToDepthLevel(ScenarioCutToDepthRequestDTO scenarioCutToDepthRequest) {
        ScenarioDTO scenario = new ScenarioDTO(scenarioCutToDepthRequest.getScenario());
        int cutDepthLevel = scenarioCutToDepthRequest.getCutDepthLevel();
        scenario.setSteps(getStepsThatDontExceedMaxDepthLevel(scenario.getSteps(), 1, cutDepthLevel));

        return scenario;
    }

    private ArrayList<ScenarioStepDTO> getStepsThatDontExceedMaxDepthLevel(
            ArrayList<ScenarioStepDTO> scenarioSteps,
            int depth,
            int maxDepth) {
        if (depth > maxDepth) {
            return new ArrayList<>();
        }

        for (ScenarioStepDTO scenarioStep : scenarioSteps) {
            scenarioStep.setSteps(getStepsThatDontExceedMaxDepthLevel(scenarioStep.getSteps(), depth + 1, maxDepth));
        }

        return scenarioSteps;
    }
}
