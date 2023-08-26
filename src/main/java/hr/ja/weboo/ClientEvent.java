package hr.ja.weboo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.ja.weboo.js.CustomCodeCommand;
import hr.ja.weboo.js.JsCommand;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ClientEvent {

    private final String eventName;
    private final String widgetId;

    @JsonIgnore
    private EventHandler serverHandler;
    private JsCommand command;

    public ClientEvent(String eventName, String widgetId) {
        this.eventName = eventName;
        this.widgetId = widgetId;
    }

    public ClientEvent handleOnClient(JsCommand command) {
        this.command = command;
        return this;
    }

//    public ClientEvent handleOnClient(String jsCode, String... params) {
//        if (!ArrayUtils.isEmpty(params)) {
//            jsCode = WebooUtil.format(jsCode, params);
//        }
//        handleOnClient(new CustomCodeCommand(jsCode));
//        return this;
//    }

    public ClientEvent handleOnServer(EventHandler handler) {
        this.serverHandler = handler;
        return this;
    }
}
