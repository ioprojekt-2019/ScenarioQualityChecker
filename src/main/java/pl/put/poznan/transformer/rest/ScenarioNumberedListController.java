package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioNumberedListService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * Controller that handles requests related to getting scenario as numbered list
 */
@RestController
public class ScenarioNumberedListController {
    private final ScenarioNumberedListService scenarioNumberedListService;

    private static final Logger logger = LoggerFactory.getLogger(ScenarioNumberedListController.class);

    public ScenarioNumberedListController(ScenarioNumberedListService scenarioNumberedListService) {
        this.scenarioNumberedListService = scenarioNumberedListService;
    }

    /**
     * Method: POST<br>
     * Endpoint: /api/scenario/numbered-steps<br>
     * @param scenarioDTO Object representing scenario created from parsed JSON body
     * @return Object representing below JSON with example values:
     *      <pre>
     *      {
     *          "list":Title: HOLIDAYS
     *      *          Actors: EMPLOYEE, EMPLOYEE
     *      *          System actors: SOFTWARE
     *      *
     *      *          1. IF EMPLOYEE REQUESTS HOLIDAYS
     *      * 	            1.1. IF EMPLOYEE REQUESTS TOO LONG HOLIDAYS
     *      * 		            1.1.1. EMPLOYEE GETS NO HOLIDAYS
     *      *          2. EMPLOYEE GETS HOLIDAYS
     *      }
     *      </pre>
     */
   @PostMapping("/scenario/numbered-steps")
    public ResponseEntity<Map<String, String>> numberedListAction(@Valid @RequestBody ScenarioDTO scenarioDTO) {
        logger.info("Operating on /scenario/numbered-steps endpoint");

        String stepsList = scenarioNumberedListService.getScenarioAsNumberedList(scenarioDTO);
        Map<String, String> response = new HashMap<>();
        response.put("numbered-steps", stepsList);

        logger.debug("Scenario as numbered list: " + stepsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
