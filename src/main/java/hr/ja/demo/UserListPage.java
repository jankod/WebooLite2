package hr.ja.demo;

import hr.ja.weboo.*;
import hr.ja.weboo.components.Col;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.components.Link;
import hr.ja.weboo.components.Row;

@Path("/users")
public class UserListPage extends Page {

    public UserListPage() {
        setLayout(new DefaultLayout());
        setTitle("User list page");

        add(new H3("User list page"));
        add(new Link("Main page", HomePage.class));

        Row row = new Row(
              new MyCol("col1"), new MyCol("col 2")
        );
        row.setStyle("border: 2px dotted;");
        add(row);

    }
}
class MyCol extends Col {
    MyCol(String t) {
        super(t);
        setStyle("border: 1px dotted");
    }
}
