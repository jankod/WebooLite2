package hr.ja.weboo.js;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JavaScript("""
      const form = $("#" + this.formId);
      $('.invalid-feedback', form).remove();
      $('.is-invalid', form).removeClass('is-invalid');
      this.errors.forEach((err) => {
          console.log(`Error field '${err.field}' message: ${err.message}`);
          $(`#${this.formId} [name='${err.field}']`).addClass('is-invalid')
              .after(`<div class="invalid-feedback">
                ${err.message}
          </div>`);
            
      });
      """)
@Getter
public class ShowFormValidationErrorsFunction extends JavaScriptFunction {

    @JavaScriptParam
    private final String formId;

    @JavaScriptParam
    private List<FieldError> errors = new ArrayList<>();

    public <M> ShowFormValidationErrorsFunction(Set<ConstraintViolation<M>> violations, String formId) {
        this.formId = formId;
        for (ConstraintViolation<M> violation : violations) {
            errors.add(new FieldError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
    }

}

@Getter
@AllArgsConstructor
class FieldError {
    String field;
    String message;
}
