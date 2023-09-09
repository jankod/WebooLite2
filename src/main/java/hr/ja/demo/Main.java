package hr.ja.demo;

import hr.ja.demo.model.User;
import hr.ja.weboo.DefaultLayout;
import hr.ja.weboo.Weboo;
import hr.ja.weboo.adminlte4.AdminLte4Layout;

public class Main {
    public static void main(String[] args) {


        initData();
        Weboo.setDefaultLayout(AdminLte4Layout.class);

        Weboo.start(8080);


    }

    private static void initData() {
        if (User.getAll().isEmpty()) {
            new User( "Janko").save();
            new User( "Pero").save();
        }
    }
}
