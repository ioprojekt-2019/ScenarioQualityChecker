package pl.put.poznan.transformer.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.put.poznan.transformer.logic.domain.dto.ErrorsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ErrorsControllerTest {
    private final String[] inputErrors = new String[]{"Error 1", "Error 2"};

    private final ErrorsController errorsController = new ErrorsController();

    @Test
    public void testValidationErrorsHandlerReturnsErrorsListAnd422HttpCode() {
        MethodArgumentNotValidException exception = prepareMockedMethodArgumentNotValidException();
        ResponseEntity<ErrorsDTO> result = errorsController.validationErrorsHandler(exception);
        assertResponseHaveErrorsListAnd422HttpCode(result);
    }

    private MethodArgumentNotValidException prepareMockedMethodArgumentNotValidException() {
        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = prepareMockedBindingResult();
        Mockito.when(exception.getBindingResult()).thenReturn(bindingResult);

        return exception;
    }

    private BindingResult prepareMockedBindingResult() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(prepareFieldErrors());

        return bindingResult;
    }

    private List<FieldError> prepareFieldErrors() {
        List<FieldError> fieldErrors = new ArrayList<>();
        for (String inputError : inputErrors) {
            fieldErrors.add(new FieldError("objectName", "fieldName", inputError));
        }

        return fieldErrors;
    }

    private void assertResponseHaveErrorsListAnd422HttpCode(ResponseEntity<ErrorsDTO> response) {
        List<String> errors = response.getBody().getErrors();
        assertEquals(inputErrors.length, errors.size());
        for (int i = 0; i < errors.size(); i++) {
            assertEquals(inputErrors[i], errors.get(i));
        }

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }
}
