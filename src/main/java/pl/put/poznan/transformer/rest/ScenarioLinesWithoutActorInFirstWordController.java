package pl.put.poznan.transformer.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioLinesWithoutActorInFirstWordService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ScenarioLinesWithoutActorInFirstWordController {
    private final ScenarioLinesWithoutActorInFirstWordService scenarioLinesWithoutActorInFirstWordService;

    public ScenarioLinesWithoutActorInFirstWordController(ScenarioLinesWithoutActorInFirstWordService scenarioLinesWithoutActorInFirstWordService) {
        this.scenarioLinesWithoutActorInFirstWordService = scenarioLinesWithoutActorInFirstWordService;
    }

    /**
     * Method: GET<br>
     * Endpoint: /api/scenario/steps/count<br>
     * @param scenarioDTO Object representing scenario created from parsed JSON body
     * @return Object representing below JSON with example values:
     *      <pre>
     *      {
     *          "lines": [
     *              "0" :"SOMETHING ACTOR",
     *              "1": "SOMETHING"
     *              ]
     *      }
     *      </pre>
     */
    @PostMapping("/scenario/lines/incorrect")
    public ResponseEntity<Map<String, Map<String, String>>> getScenarioLinesWithoutActorInFirstWordAction(@RequestBody ScenarioDTO scenarioDTO) {
        ArrayList<String> lines = scenarioLinesWithoutActorInFirstWordService.getLines(scenarioDTO);
        Map<String, String> countedLines = new HashMap<>();

        for(int i=0; i<lines.size(); i++) {
            countedLines.put(Integer.toString(i), lines.get(i));
        }

        Map<String, Map<String, String>> response = new HashMap<>();
        response.put("lines", countedLines);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
