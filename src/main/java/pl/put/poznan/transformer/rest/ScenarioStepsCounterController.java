package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioStepsCounterService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ScenarioStepsCounterController {
    private final ScenarioStepsCounterService scenarioStepsCounterService;

    private static final Logger logger = LoggerFactory.getLogger(ScenarioStepsCounterController.class);

    public ScenarioStepsCounterController(ScenarioStepsCounterService scenarioStepsCounterService) {
        this.scenarioStepsCounterService = scenarioStepsCounterService;
    }

    /**
     * Method: POST<br>
     * Endpoint: /api/scenario/steps/count<br>
     * @param scenarioDTO Object representing scenario created from parsed JSON body
     * @return Object representing below JSON with example values:
     *      <pre>
     *      {
     *          "count": 12
     *      }
     *      </pre>
     */
    @PostMapping("/scenario/steps/count")
    public ResponseEntity<Map<String, Integer>> countScenarioStepsAction(@Valid @RequestBody ScenarioDTO scenarioDTO) {
        int stepsCount = scenarioStepsCounterService.getCount(scenarioDTO);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", stepsCount);

        logger.debug("Scenario steps number: " + stepsCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
