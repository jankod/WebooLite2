package hr.ja.demo;

import hr.ja.demo.model.User;
import hr.ja.weboo.*;

import java.util.function.Predicate;


@Path("/user/edit")
public class UserEditPage extends Page {


    UserEditPage() {
        setTitle("User edit");
        setLayout(new DefaultLayout());

        String id = Context.req().queryParams("userId");
        User u = User.findById(Long.valueOf(id));

        dump(u);

    }


}
