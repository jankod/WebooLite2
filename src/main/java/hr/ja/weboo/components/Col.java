package hr.ja.weboo.components;


import hr.ja.weboo.Widget;

public class Col extends SimpleTag {

    public Col(String text) {
        this(new JustText( text));
    }

    public Col(Widget... widgets) {
        super("div", widgets);
        setClasses("col");
    }

}
