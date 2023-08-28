package hr.ja.weboo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.ja.weboo.js.JsCommand;
import lombok.Data;

@Data
public class ClientEvent {

    private final String eventName;
    private final String widgetId;


    /**
     * event.preventDefault();
     * event.stopPropagation();
     */
    private boolean stopPropagation = true;


    @JsonIgnore
    private EventHandler serverHandler;
    private JsCommand command;

    public ClientEvent(String eventName, String widgetId) {
        this.eventName = eventName;
        this.widgetId = widgetId;
        // napravi komandu za on eventName widgetId
    }

    public ClientEvent handleOnClient(JsCommand command) {
        this.command = command;
        return this;
    }

    public ClientEvent handleOnServer(EventHandler handler) {
        this.serverHandler = handler;
        return this;
    }

    public void build() {

    }
}
