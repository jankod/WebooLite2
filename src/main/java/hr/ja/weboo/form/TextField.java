package hr.ja.weboo.form;

import hr.ja.weboo.Context;
import hr.ja.weboo.WebooUtil;

import java.beans.PropertyDescriptor;
import java.util.Map;

public class TextField extends FormFieldWidget {

    public TextField(String name, String label) {
        this.name = name;
        this.label = label;
    }

    @Override
    public String toHtml() {
        addClass("mb-3");
        String html = """
              <div {attr.raw}>
                  <label for="{name}" class="form-label">{label}</label>
                  <input type="text" class="form-control {errorClass}" id="{name}" name="{name}" {autofocus} value='{value}' placeholder='{placeholder}'>
                  {#for err in errorMessages}
                  <div class="invalid-feedback">
                      {err}
                  </div>
                  {/for}
              </div>
                           """;
        return WebooUtil.qute(html, Map.of(
              "id", this.getWidgetId(),
              "attr", getIdClassStyleAttr(),
              "name", name,
              "label", label,
              "errorMessages", errorMessages,
              "errorClass", getErrorClass(),
              "value", value,
              "autofocus", isAutofocus() ? "autofocus" : "",
              "placeholder", placeholder
        ));

    }

    public String getSubmitedValue() {
        return Context.req().queryParams(name);
    }

    @Override
    public void bindTo(Object modelObject) {
        String value = Context.req().queryParams(name);
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, modelObject.getClass());
            propertyDescriptor.getWriteMethod().invoke(modelObject, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
