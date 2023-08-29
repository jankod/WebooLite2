package hr.ja.weboo.js;

import hr.ja.weboo.components.Button;

public class WidgetDemo {
    public static void main(String[] args) {


        Button b = new Button("dela");

        System.out.println(b.toHtml());

    }
}
