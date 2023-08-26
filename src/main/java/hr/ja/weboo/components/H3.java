package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

public class H3 extends Widget {

    private final String text;

    public H3(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return "<h3>" + WebooUtil.escape(text) + "</h3>";
    }
}
