package hr.ja.weboo.form;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.experimental.StandardException;

import java.util.Set;

@StandardException
public class FormException extends Exception {

    @Getter
    private Set<ConstraintViolation<Object>> constraintViolations;

    public <T> FormException(Set<ConstraintViolation<Object>> constraintViolations) {
        super();
        this.constraintViolations = constraintViolations;
    }
}
