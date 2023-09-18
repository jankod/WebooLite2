package hr.ja.weboo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

import static org.apache.commons.lang3.StringUtils.*;

@Getter
@Setter
@Accessors(chain = true)
@Slf4j
public abstract class Widget extends TemplateWidgetUtil {

    private String widgetId = WebooUtil.wigetNewId(this.getClass());

    private String classes = "";

    private String style = "";

    private final LinkedList<Widget> children = new LinkedList<>();

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

    public ClientServerEvent on(String eventName) {
        ClientServerEvent clientEvent = new ClientServerEvent(eventName, widgetId);
        // TODO: krivo, ide u static a ne u request contrxt od page
        //PageRequestContext.add(clientEvent, Context.getPageId());
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

        // TODO: escape classes and styles
        return paramValue("id", getWidgetId())
               + paramValue("class", getClasses())
               + paramValue("style", getStyle());

    }

    private String paramValue(String param, String value) {
        if (isBlank(value)) {
            return "";
        }
        return "%s=\"%s\" ".formatted(param, value);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public void hiden() {
        addClass("d-none");
    }
}
