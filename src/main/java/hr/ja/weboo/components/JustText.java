package hr.ja.weboo.components;

import hr.ja.weboo.Widget;

public class JustText extends Widget {
    private final String text;

    public JustText(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return text;
    }
}
