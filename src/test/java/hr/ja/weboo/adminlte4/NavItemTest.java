package hr.ja.weboo.adminlte4;

import hr.ja.weboo.Div;

import static org.junit.jupiter.api.Assertions.*;

class NavItemTest {

    public static void main(String[] args) {


        NavItem item = new NavItem("icon", "/pathg/sd", new Div("Link label"));
        item.add(new NavItem("cc", "ppap", new Div("child")));

        System.out.println(item.toHtml());
    }

}
