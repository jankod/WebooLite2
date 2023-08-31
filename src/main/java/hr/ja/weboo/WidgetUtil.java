package hr.ja.weboo;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
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

    /**
     * keyValues are key1, value2, key2, value2, ....
     * @param keyValues
     * @return pairs
     */
    public static List<Pair<Object, Object>> toPairs(Object... keyValues) {

        List<Pair<Object, Object>> pairs = new ArrayList<>();
        if (keyValues == null || keyValues.length % 2 != 0) {
            return pairs;
        }
        for (int i = 0; i < keyValues.length; i += 2) {
            pairs.add(new MutablePair<>(keyValues[i], keyValues[i + 1]));
        }
        return pairs;
    }
}
