package hr.ja.weboo.components;

import hr.ja.weboo.Widget;
import hr.ja.weboo.components.Col;
import hr.ja.weboo.components.SimpleTag;

public class Span extends SimpleTag {

    public Span(Widget... widgets) {
        super("span", widgets);
    }

    public Span(String text) {
        super("span");
        super.setText(text);
    }

    public static void main(String[] args) {
        Span div = new Span(new Col());
        System.out.println(div.toHtml());
    }
}
