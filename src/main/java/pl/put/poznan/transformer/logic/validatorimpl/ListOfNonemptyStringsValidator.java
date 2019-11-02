package pl.put.poznan.transformer.logic.validatorimpl;

import pl.put.poznan.transformer.logic.validator.ListOfNonemptyStrings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListOfNonemptyStringsValidator implements ConstraintValidator<ListOfNonemptyStrings, List<String>> {
    @Override
    public void initialize(ListOfNonemptyStrings listOfNonemptyStrings) {
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        return strings.stream().allMatch(str -> str != null && str.length() != 0);
    }
}
