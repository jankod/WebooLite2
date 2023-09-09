package hr.ja.weboo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Getter
@Setter
@Accessors(chain = true)
@Slf4j
public abstract class Widget extends TemplateWidgetUtil {

    private String widgetId = WebooUtil.wigetNewId(this.getClass());

    private String classes = "";

    private String style = "";

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


    public ClientServerEvent on(String eventName) {
        ClientServerEvent clientEvent = new ClientServerEvent(eventName, widgetId);
        PageSessionManager.add(clientEvent, Context.getPageId());
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

        return paramValue("id", getWidgetId())
               + paramValue("class", getClasses())
               + paramValue("style", getStyle());


//        return WebooUtil.qute("""
//                    id="{id}" class="{class}" style="{style}"
//                    """, Map.of(
//                    "id", getWidgetId(),
//                    "class", getClasses(),
//                    "style", getStyle()
//              )
//        );
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
