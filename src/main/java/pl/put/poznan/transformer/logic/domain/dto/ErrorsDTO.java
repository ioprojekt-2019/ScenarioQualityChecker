package pl.put.poznan.transformer.logic.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDTO {
    private List<String> errors;

    public ErrorsDTO() {
        errors = new ArrayList<>();
    }

    public ErrorsDTO(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        errors.add(error);
    }
}
