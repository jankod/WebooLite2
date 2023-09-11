package hr.ja.weboo;

import hr.ja.weboo.components.Col;
import hr.ja.weboo.components.SimpleTag;

public class EmptyText extends Widget {


    private final String text;

    public EmptyText(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return WebooUtil.escape(text);
    }
}
