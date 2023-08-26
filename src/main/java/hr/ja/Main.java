package hr.ja;

import hr.ja.weboo.Weboo;

public class Main {
    public static void main(String[] args) {

        Weboo.addPage(MainPage.class);
        Weboo.start(8080);


    }
}
