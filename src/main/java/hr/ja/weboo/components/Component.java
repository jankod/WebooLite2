package hr.ja.weboo.components;

import hr.ja.weboo.Widget;
import hr.ja.weboo.WidgetUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Component extends Widget {

    private final List<Widget> children = new ArrayList<>(2);

    public Component(Widget... widgets) {
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

}
