package pl.put.poznan.transformer.logic.serviceimpl;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioStepDTO;
import pl.put.poznan.transformer.logic.service.ScenarioLinesWithoutActorInFirstWordService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScenarioLinesWithoutActorInFirstWordServiceImpl implements ScenarioLinesWithoutActorInFirstWordService {

    private Set<String> actors;

    @Override
    public ArrayList<String> getLines(ScenarioDTO scenarioDTO) {

        this.actors = new HashSet<>();
        actors.addAll(scenarioDTO.getActors());
        actors.add(scenarioDTO.getSystemActor());

        return new ArrayList<String>(getIncorrectNestedStepsNames(scenarioDTO.getSteps()));
    }

    private ArrayList<String> getIncorrectNestedStepsNames(ArrayList<ScenarioStepDTO> nestedSteps) {

        ArrayList<String> returnLines = new ArrayList<>();

        for(ScenarioStepDTO step: nestedSteps) {
            String name = step.getName();

            if(!isLineCorrect(name)) {
                returnLines.add(name);
            }

            returnLines.addAll(getIncorrectNestedStepsNames(step.getSteps()));
        }

        return returnLines;
    }

    private boolean isLineCorrect(String line) {
        boolean matches = false;

        for(String actorName: actors) {
            String[] words = line.split(" ");
            matches = actorName.equals(words[0]);

            if(matches) {
                break;
            }
        }

        return matches;
    }
}
