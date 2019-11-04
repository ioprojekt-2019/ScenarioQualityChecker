package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest", "pl.put.poznan.transformer.logic"})
public class ScenarioQualityChecker {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioQualityChecker.class, args);
    }
}
