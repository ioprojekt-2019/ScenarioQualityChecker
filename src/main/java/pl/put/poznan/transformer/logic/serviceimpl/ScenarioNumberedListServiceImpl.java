package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioKeywordsCounterService;
import pl.put.poznan.transformer.logic.service.ScenarioNumberedListService;

import java.util.ArrayList;

@Service
public class ScenarioNumberedListServiceImpl implements ScenarioNumberedListService {

    @Override
    public String getScenarioAsNumberedList(ScenarioDTO scenarioDTO) {
        return createNumberedListString(scenarioDTO);
    }

    private String createNumberedListString(ScenarioDTO scenario) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Title: %s\n", scenario.getTitle()));
        sb.append("Actors: ");
        sb.append(String.join(", ", scenario.getActors()));
        sb.append(String.format("\nSystem actor: %s\n\n", scenario.getSystemActor()));
        ArrayList<ScenarioStepDTO> steps = scenario.getSteps();
        if(steps.size() <= 0){
            return sb.toString();
        }
        ArrayList<Integer> currentStep = new ArrayList<Integer>();
        currentStep.add(1);
        String stepNumberedList = getListOnGivenDepth(steps, 0, currentStep);
        sb.append(stepNumberedList);
        return sb.toString();
    }

    private String getListOnGivenDepth(ArrayList<ScenarioStepDTO> steps, int depth, ArrayList<Integer> currentStep){
        StringBuilder sb = new StringBuilder();
        int stepIndex = 1;
        if(depth+1 > currentStep.size()){
            currentStep.add(stepIndex);
        }
        for(ScenarioStepDTO step : steps){
            for(int i = 0; i < depth; i++){
                sb.append("\t");
            }
            for(int num : currentStep){
                sb.append(String.format("%d.", num));
            }
            sb.append(String.format(" %s", step.getName()));
            sb.append("\n");
            sb.append(getListOnGivenDepth(step.getSteps(), depth+1, new ArrayList<>(currentStep)));
            stepIndex++;
            currentStep.set(depth, stepIndex);
        }
        return  sb.toString();
    }

}
