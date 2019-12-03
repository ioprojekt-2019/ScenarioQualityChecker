package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioCutToDepthRequestDTO;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;
import pl.put.poznan.transformer.logic.service.ScenarioCutToDepthService;

import javax.validation.Valid;

/**
 * Controller containing endpoint for transforming input ScenarioDTO to ScenarioDTO
 * with steps cut to given depth level
 * */
@RestController
public class ScenarioCutToDepthController {
    private final ScenarioCutToDepthService scenarioCutToDepthService;

    private static final Logger logger = LoggerFactory.getLogger(ScenarioCutToDepthController.class);

    public ScenarioCutToDepthController(ScenarioCutToDepthService scenarioCutToDepthService) {
        this.scenarioCutToDepthService = scenarioCutToDepthService;
    }

    /**
     * Method: POST<br>
     * Endpoint: /api/scenario/cut-to-depth<br>
     *
     * @param scenarioCutToDepthRequest ScenarioCutToDepthRequestDTO Object representing JSON request body.
     * Request body should looks like follow:<br>
     * <pre>
     *      {
     *          "cutDepthLevel": 3, #depth level to which scenario should be cut
     *          "scenario": ScenarioDTO
     *      }
     * </pre>
     * @return Result of cut scenario to given depth level operation ScenarioDTO instance parsed to JSON
     */
    @PostMapping("/scenario/cut-to-depth")
    public ResponseEntity<ScenarioDTO> cutScenarioToDepthAction(
            @Valid @RequestBody ScenarioCutToDepthRequestDTO scenarioCutToDepthRequest) {
        logger.info("Operating on /scenario/cut-to-depth endpoint");

        ScenarioDTO cutScenario = scenarioCutToDepthService.cutScenarioToDepthLevel(scenarioCutToDepthRequest);

        return new ResponseEntity<>(cutScenario, HttpStatus.OK);
    }
}
