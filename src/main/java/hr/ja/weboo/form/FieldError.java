package hr.ja.weboo.form;

import lombok.Data;

@Data
public class FieldError {


    private String field;
    private String message;

    // TODO: multiple errors add
    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
