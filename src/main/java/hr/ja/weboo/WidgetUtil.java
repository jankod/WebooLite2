package hr.ja.weboo;

import java.util.List;

public class WidgetUtil {

    public static String widgetToHtml(List<Widget> widgets) {
        if (widgets == null) {
            return "";
        }

        if (widgets.isEmpty()) {
            return "";
        }
        StringBuilder html = new StringBuilder();
        for (Widget widget : widgets) {
            html.append(widget.toHtml()).append("\n");
        }
        return html.toString();
    }


    public static String pageToHtml(Page page) {
        List<Widget> widgetList = page.getWidgets();
        return widgetToHtml(widgetList);
    }
}
