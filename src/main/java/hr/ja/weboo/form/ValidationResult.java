package hr.ja.weboo.form;

import lombok.Data;

@Data
public class ValidationResult<T> {
    private T model;
    private AjaxFormResult ajaxResult;

    public void addFormError(String errorOnProcessingForm) {
        if (ajaxResult == null) {
            ajaxResult = new AjaxFormResult();
        }
        ajaxResult.addError(errorOnProcessingForm);
    }
}
