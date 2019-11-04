package pl.put.poznan.transformer.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.put.poznan.transformer.logic.domain.dto.ErrorsDTO;

import java.util.List;

@RestControllerAdvice
public class ErrorsController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsDTO> validationErrorsHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<>(processErrors(fieldErrors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ErrorsDTO processErrors(List<FieldError> fieldErrors) {
        ErrorsDTO errorsDTO = new ErrorsDTO();
        for (FieldError fieldError : fieldErrors) {
            errorsDTO.addError(fieldError.getDefaultMessage());
        }

        return errorsDTO;
    }
}
