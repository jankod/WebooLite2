package hr.ja.demo;

import hr.ja.demo.model.User;
import hr.ja.weboo.DefaultLayout;
import hr.ja.weboo.Weboo;

public class Main {
    public static void main(String[] args) {


        initData();

        Weboo.start(8080);


    }

    private static void initData() {
        if (User.getAll().isEmpty()) {
            new User( "Janko").save();
            new User( "Pero").save();
        }
    }
}
