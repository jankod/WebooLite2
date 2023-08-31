package hr.ja.demo;

import hr.ja.demo.model.User;
import hr.ja.weboo.*;
import hr.ja.weboo.components.*;

import java.util.function.Function;

@Path("/users")
public class UserListPage extends Page {

    public UserListPage() {
        setLayout(new DefaultLayout());

        setTitle("User list page");

        add(new H3("User list page"));
        add(new Link("Main page", HomePage.class));
        add(new Link("Add User", UserAddPage.class));


        SimpleTable<User> table = new SimpleTable<>(User.getAll());

        table.column("ID", model -> new Bold(model.getId()+""));
        table.column("Name", u -> new Link(u.getName(), UserEditPage.class, "userId", u.getId()));


        Card userList = new Card("User list");
        userList.add(table);
        add(userList);


    }
}
