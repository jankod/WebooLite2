package hr.ja.weboo;

public class Div extends SimpleTag {

    public Div(Widget... widgets) {
        super("div", widgets);
    }

    public Div(String text) {
        super("div");
        super.setText(text);
    }

    public static void main(String[] args) {
        Div div = new Div(new Col());
        div.attr("title", "< > nisam");
        System.out.println(div.toHtml());
    }
}
