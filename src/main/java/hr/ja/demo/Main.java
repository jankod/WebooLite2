package hr.ja.demo;

import hr.ja.weboo.DefaultLayout;
import hr.ja.weboo.Weboo;

public class Main {
    public static void main(String[] args) {

        Weboo.addPage(MainPage.class);
        Weboo.addPage(UserListPage.class);
        Weboo.addPage(UserAddPage.class);


        //Weboo.setDefaultLayout(new DefaultLayout());

        Weboo.start(8080);


    }
}
