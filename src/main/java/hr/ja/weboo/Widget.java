package hr.ja.weboo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public abstract class Widget {

    @Setter
    private String widgetId = WebooUtil.wigetNewId(this.getClass());

    private String classes = "";

    private List<ClientEvent> clientEvents = new ArrayList<>();

    public ClientEvent on(String eventName) {
        ClientEvent clientEvent = new ClientEvent(eventName, widgetId);
        clientEvents.add(clientEvent);
        return clientEvent;
    }

    public Widget setClasses(String classes) {
        this.classes = classes;
        return this;
    }

    public void addClass(String classes) {
        this.classes += " " + classes;
    }


    public abstract String toHtml();

    protected String getIdClassAttr() {
        String eventsAttr = prepareEvents();

        String attr = WebooUtil.qute("""
                    id="{id}" class="{class}"
                    """, Map.of(
                    "id", getWidgetId(),
                    "class", getClasses()
              )
        );
   //     log.debug("attr '{}'", attr);

        return attr;
    }

    private String prepareEvents() {
        if (clientEvents.isEmpty()) {
            return "";
        }
        String jsCode = "";
        for (ClientEvent ev : clientEvents) {
            //ev.getEventName();
        }
        return " data-weboo-events=\"%s\" ".formatted(jsCode);

    }

    protected void handleEvents() {

    }
}
