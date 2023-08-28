package hr.ja.weboo;


import hr.ja.weboo.components.JustText;
import org.w3c.dom.Text;

public class Col extends SimpleTag {

    public Col(String text) {
        this(new JustText( text));
    }

    public Col(Widget... widgets) {
        super("div", widgets);
        setClasses("col");
    }

}
