package hr.ja.weboo.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import hr.ja.weboo.Context;
import hr.ja.weboo.WebooUtil;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class FormData {

    public String getValue(String fieldName) {
        return Context.req().queryParams(fieldName);
    }

    public <T> ValidationResult<T> bindAndValidate(Class<T> clazz) {
        ValidationResult<T> validationResult = new ValidationResult<T>();
        String json = Context.req().body();
        //  log.debug("Json body: {}", json);
        try {
            T obj = WebooUtil.fromJson(json, clazz);
            validationResult.setModel(obj);
            Set<ConstraintViolation<T>> constraintViolations = WebooUtil.validate(obj);
            AjaxFormResult ajaxResult = new AjaxFormResult(constraintViolations);
            validationResult.setAjaxResult(ajaxResult);

        } catch (JsonProcessingException e) {
            log.debug("Error ", e);
            validationResult.addFormError("Error on processing form: "+ e.getMessage() + ".");
        }
        return validationResult;

    }

}
