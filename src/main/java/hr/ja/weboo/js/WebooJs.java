package hr.ja.weboo.js;

import hr.ja.demo.User;
import hr.ja.weboo.form.Form;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class WebooJs {

    public static JsCommand onEvent(String widgetId, String eventName, String handlerId, JsCommand jsCommand) {

        return jsCommand;
    }

    public static JsCommand submitFormCommand(Form form) {

        return new SubmitFormCommand(form.getWidgetId());
    }

    public static JsCommand showValidationErrors(Set<ConstraintViolation<User>> violations, String widgetId) {
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
class OnEventCommand extends JsCommand{

}
