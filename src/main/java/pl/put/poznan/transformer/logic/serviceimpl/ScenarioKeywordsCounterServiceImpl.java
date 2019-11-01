package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioKeywordsCounterService;

import java.util.ArrayList;

@Service
public class ScenarioKeywordsCounterServiceImpl implements ScenarioKeywordsCounterService {

    private final String[] keywords = {"IF", "ELSE", "FOR EACH"};

    @Override
    public int getNumberOfKeywords(ScenarioDTO scenarioDTO) {
        return calculateNumberOfKeywords(scenarioDTO.getSteps());
    }

    private int calculateNumberOfKeywords(ArrayList<ScenarioStepDTO> steps) {
        int keywordsCount = 0;
        for (ScenarioStepDTO step : steps) {
            for (String keyword : keywords) {
                if (step.getName() != null && step.getName().startsWith(keyword)) {
                    keywordsCount++;
                }
            }
            keywordsCount += calculateNumberOfKeywords(step.getSteps());
        }

        return keywordsCount;
    }
}
