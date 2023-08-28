package hr.ja.weboo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@Slf4j
public abstract class Widget {


    private String widgetId = WebooUtil.wigetNewId(this.getClass());

    private String classes = "";

    private String style = "";

    private List<ClientEvent> clientEvents = new ArrayList<>();

    private final List<Widget> children = new ArrayList<>(2);

    public Widget(Widget... widgets) {
        addAll(widgets);
    }

    public <T extends Widget> Widget add(Widget c) {
        children.add(c);
        return c;
    }

    public void addAll(Widget[] widgets) {
        if (widgets != null) {
            for (Widget w : widgets) {
                add(w);
            }
        }
    }

    public String toChildrenHtml() {
        return WidgetUtil.widgetToHtml(getChildren());
    }


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

    protected String getIdClassStyleAttr() {

        return WebooUtil.qute("""
                    id="{id}" class="{class}" style="{style}"
                    """, Map.of(
                    "id", getWidgetId(),
                    "class", getClasses(),
                    "style", getStyle()
              )
        );
    }


    public boolean hasChildren() {
        return !children.isEmpty();
    }
}
