package hr.ja.weboo.js;

import hr.ja.weboo.ClientServerEvent;
import lombok.Getter;
import lombok.experimental.Accessors;

@JavaScript("""
          $("#"+this.widegetId).on(this.eventName, function (e) {
                e.stopPropagation();
                        
          });
      """)
@Getter
@Accessors(chain = true)
public class ClientEventCommand extends JavaScriptFunction {

    @JavaScriptParam
    private final String eventName;

    @JavaScriptParam
    private final String widgetId;


    private String clientJs;

    public ClientEventCommand(String eventName, String widgetId) {
        this.eventName = eventName;
        this.widgetId = widgetId;
    }

    public ClientEventCommand(ClientServerEvent clientEvent) {
        this.eventName = clientEvent.getEventName();
        this.widgetId = clientEvent.getWidgetId();


    }

    public void client(String js) {
        this.clientJs = js;
    }
}
