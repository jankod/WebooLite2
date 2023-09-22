package hr.ja.demo;

import hr.ja.demo.model.Role;
import hr.ja.demo.model.User;
import hr.ja.weboo.*;
import hr.ja.weboo.adminlte4.AdminLte4Layout;
import hr.ja.weboo.tabler.TablerLayout;
import spark.Spark;

public class Main {
    public static void main(String[] args) {


        initData();
        Weboo.setDefaultLayout(TablerLayout.class);



        Weboo.addPageFilter(new PageFilter() {
            @Override
            public boolean allow(Class<? extends Page> page) {
                if (page.isAssignableFrom(HomePage.class)) {
                    User user = Context.req().session(true).attribute("user");
                    if (user != null) {
                        if (user.getRole().equals(Role.USER)) {
                            // ...

                        }
                    }


                }
                return true;
            }
        });

        Weboo.start(8080);


    }

    private static void initData() {
        if (User.getAll().isEmpty()) {
            new User("Janko").save();
            new User("Pero").save();
        }
    }
}
