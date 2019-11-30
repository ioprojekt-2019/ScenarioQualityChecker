package pl.put.poznan.transformer.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.FindScenarioStepNamesNotStartingWithActorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@RestController
public class FindScenarioStepNamesNotStartingWithActorServiceController {
    private final FindScenarioStepNamesNotStartingWithActorService findScenarioStepNamesNotStartingWithActorService;

    public FindScenarioStepNamesNotStartingWithActorServiceController(FindScenarioStepNamesNotStartingWithActorService findScenarioStepNamesNotStartingWithActorService) {
        this.findScenarioStepNamesNotStartingWithActorService = findScenarioStepNamesNotStartingWithActorService;
    }

    /**
     * Method: Post<br>
     * Endpoint: /api/scenario/steps/not-starting-with-actor<br>
     * @param scenarioDTO Object representing scenario created from parsed JSON body
     * @return JSON containing incorrect step names:
     *      <pre>
     *      {
     *          "lines": ["SOMETHING ACTOR", "SOMETHING", "IF SOMETHING"]
     *      }
     *      </pre>
     */
    @PostMapping("/scenario/steps/not-starting-with-actor")
    public ResponseEntity<Map<String, ArrayList<String>>> getFindScenarioLinesWithoutActorInFirstWordAction(@Valid @RequestBody ScenarioDTO scenarioDTO) {
        ArrayList<String> lines = findScenarioStepNamesNotStartingWithActorService.findStepNames(scenarioDTO);

        Map<String, ArrayList<String>> response = new HashMap<>();
        response.put("lines", lines);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
