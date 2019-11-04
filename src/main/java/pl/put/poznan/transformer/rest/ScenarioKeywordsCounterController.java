package pl.put.poznan.transformer.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    public ScenarioKeywordsCounterController(ScenarioKeywordsCounterService scenarioKeywordsCounterService) {
        this.scenarioKeywordsCounterService = scenarioKeywordsCounterService;
    }

    /**
     * Method: GET
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
    @GetMapping("/scenario/keywords/count")
    public ResponseEntity<Map<String, Integer>> countScenarioKeywordsAction(@Valid @RequestBody ScenarioDTO scenarioDTO) {
        int keywordsCount = scenarioKeywordsCounterService.getNumberOfKeywords(scenarioDTO);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", keywordsCount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
