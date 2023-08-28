package hr.ja.weboo.form;

import hr.ja.weboo.Widget;
import lombok.Getter;
import lombok.Setter;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class FormFieldWidget extends Widget {

    protected String name = "";
    protected String label = "";
    protected String value = "";

    protected String placeholder = "";

    private boolean autofocus = false;

    protected List<String> errorMessages = new ArrayList<>();

    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    public void autofocus() {
        this.autofocus = true;
    }

    public String getErrorClass() {
        String errorClass = "";

        if (!CollectionUtils.isEmpty(errorMessages)) {
            errorClass = "is-invalid";
        }
        return errorClass;
    }

    public abstract void bindTo(Object modelObject) ;
}
