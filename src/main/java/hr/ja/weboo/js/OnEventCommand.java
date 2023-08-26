package hr.ja.weboo.js;

import hr.ja.weboo.ClientEvent;

@JavaScript("""
          var function1 = new Function("2", jsCode).call();
          $('#'+widgetId).on(eventName, function1);
      """)
public class OnEventCommand extends JsCommand {

    public OnEventCommand(ClientEvent event) {

    }
}
