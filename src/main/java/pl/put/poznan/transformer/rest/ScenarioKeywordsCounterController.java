package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioKeywordsCounterService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller that handles requests related to keyword counting
 */
@RestController
public class ScenarioKeywordsCounterController {
    private final ScenarioKeywordsCounterService scenarioKeywordsCounterService;

    private static final Logger logger = LoggerFactory.getLogger(ScenarioStepsCounterController.class);

    public ScenarioKeywordsCounterController(ScenarioKeywordsCounterService scenarioKeywordsCounterService) {
        this.scenarioKeywordsCounterService = scenarioKeywordsCounterService;
    }

    /**
     * Method: POST
     * Endpoint: /api/scenario/keywords/count
     *
     * @param scenarioDTO scenario object representing parsed JSON body
     * @return JSON containing calculated value:
     * <pre>
     *      {
     *          "count": number of keywords
     *      }
     * </pre>
     */
    @PostMapping("/scenario/keywords/count")
    public ResponseEntity<Map<String, Integer>> countScenarioKeywordsAction(@Valid @RequestBody ScenarioDTO scenarioDTO) {
        logger.info("Operating on /scenario/keywords/count endpoint");

        int keywordsCount = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", keywordsCount);

        logger.debug("Number of keywords in a scenario: " + keywordsCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
