package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioStepsCounterService;
import java.util.ArrayList;

@Service
public class ScenarioStepsCounterServiceImpl implements ScenarioStepsCounterService {
    @Override
    public int getCount(ScenarioDTO scenarioDTO) {
        return calculateStepsHelper(scenarioDTO.getSteps());
    }

    private int calculateStepsHelper(ArrayList<ScenarioStepDTO> steps) {
        int stepsCount = 0;
        for (ScenarioStepDTO step : steps) {
            stepsCount += calculateStepsHelper(step.getSteps());
        }

        return stepsCount + steps.size();
    }
}
