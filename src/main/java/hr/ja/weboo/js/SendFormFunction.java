package hr.ja.weboo.js;

import hr.ja.weboo.form.Form;
import lombok.Getter;

@JavaScript("""
          let jsonForm = weboo.convertFormToJSON(this.formId);
          const json = JSON.stringify(jsonForm);
          return json;
      """)
@Getter
public class SendFormFunction extends JavaScriptFunction {

    @JavaScriptParam
    private String formId;

    public SendFormFunction(Form form) {
        formId = form.getWidgetId();
    }
}
