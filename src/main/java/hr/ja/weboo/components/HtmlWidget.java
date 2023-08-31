package hr.ja.weboo.components;

import hr.ja.weboo.Widget;

public class HtmlWidget extends Widget {
    private final String html;

    public HtmlWidget(String html) {

        this.html = html;
    }

    @Override
    public String toHtml() {
        return html;
    }
}
