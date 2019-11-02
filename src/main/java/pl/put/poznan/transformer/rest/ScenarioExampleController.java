package pl.put.poznan.transformer.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.domain.dto.ScenarioDTO;

import javax.validation.Valid;

//TODO remove example controller
@RestController
public class ScenarioExampleController {
    @PostMapping("/scenario/example")
    public ResponseEntity<ScenarioDTO> dummyScenarioAction(@Valid @RequestBody ScenarioDTO scenario) {
        return new ResponseEntity<>(scenario, HttpStatus.CREATED);
    }
}
