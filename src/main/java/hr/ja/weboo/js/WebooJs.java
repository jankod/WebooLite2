package hr.ja.weboo.js;

import hr.ja.demo.User;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class WebooJs {

    public static JavaScriptFunction onEvent(String widgetId, String eventName, String handlerId, JavaScriptFunction jsCommand) {

        return jsCommand;
    }

    public static JavaScriptFunction sendFormToServer(String formId, String jsCode) {
        return new SubmitFormCommand(formId);
    }

    public static JavaScriptFunction showValidationErrors(Set<ConstraintViolation<User>> violations, String widgetId) {
        FormValidationErrorCommand c;
        return null;
    }
}
@JavaScript("""
          weboo.onEvent = function (widgetId, eventName, handlerId, jsonCommandData) {
                  $("#" + widgetId).on(eventName, function (e) {
                      jsonCommandData.event = e;
                      const result = weboo.exeCommand(jsonCommandData);
                      weboo.handleEventOnServer(widgetId, eventName, handlerId, result);
                  });
              }
      """)
class OnEventCommand extends JavaScriptFunction {

}
