package pl.put.poznan.transformer.logic.validator;

import pl.put.poznan.transformer.logic.validatorimpl.ListOfNonemptyStringsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListOfNonemptyStringsValidator.class)
public @interface ListOfNonemptyStrings {
    public String message() default "Each entry in the list must be a nonempty string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}