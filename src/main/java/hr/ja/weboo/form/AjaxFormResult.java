package hr.ja.weboo.form;

import hr.ja.weboo.js.AjaxResult;
import jakarta.validation.ConstraintViolation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AjaxFormResult extends AjaxResult {

    private final List<FieldError> formErrors = new ArrayList<>();

    public AjaxFormResult(String globalError) {
        this.addError(globalError);
    }


    public <T> AjaxFormResult(Set<ConstraintViolation<T>> constraint) {
        for (ConstraintViolation<T> c : constraint) {
            formErrors.add(new FieldError(c.getPropertyPath().toString(), c.getMessage()));
        }
    }

    public void addFieldError(String field, String message) {
        formErrors.add(new FieldError(field, message));
    }

    public boolean hasError() {
        return super.hasError() || !formErrors.isEmpty();
    }
}
