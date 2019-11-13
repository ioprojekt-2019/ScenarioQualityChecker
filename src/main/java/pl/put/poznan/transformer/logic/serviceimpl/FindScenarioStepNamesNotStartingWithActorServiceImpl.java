package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.FindScenarioStepNamesNotStartingWithActorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FindScenarioStepNamesNotStartingWithActorServiceImpl implements FindScenarioStepNamesNotStartingWithActorService {

    private String[] keywords;
    private Set<String> actors;

    public FindScenarioStepNamesNotStartingWithActorServiceImpl(@Value("${scenario.keywords}") String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public ArrayList<String> findStepNames(ScenarioDTO scenarioDTO) {
        this.actors = new HashSet<>();
        actors.addAll(scenarioDTO.getActors());
        actors.add(scenarioDTO.getSystemActor());

        return new ArrayList<String>(search(scenarioDTO.getSteps()));
    }

    private ArrayList<String> search(ArrayList<ScenarioStepDTO> steps) {
        ArrayList<String> returnStepsNames = new ArrayList<>();

        for(ScenarioStepDTO step: steps) {
            String name = step.getName();
            name = ignoreKeywords(name);

            if(!isStepNameCorrect(name)) {
                returnStepsNames.add(name);
            }

            returnStepsNames.addAll(search(step.getSteps()));
        }

        return returnStepsNames;
    }

    private String ignoreKeywords(String line) {
        for (String keyword : keywords) {
            line = line.replaceFirst("^" + keyword + " ", "");
        }

        return line;
    }

    private boolean isStepNameCorrect(String line) {
        return actors.stream().anyMatch(actor -> Pattern.compile("^" + actor + " ").matcher(line).find());
    }
}
