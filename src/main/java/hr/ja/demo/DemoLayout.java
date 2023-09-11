package hr.ja.demo;

import hr.ja.weboo.EmptyText;
import hr.ja.weboo.Weboo;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.WidgetUtil;
import hr.ja.weboo.adminlte4.AdminLte4Layout;
import hr.ja.weboo.adminlte4.NavItem;

public class DemoLayout extends AdminLte4Layout {

    public DemoLayout() {
        NavItem dashboard = new NavItem("bi-speedometer", "#", new EmptyText("Dashboard"));
        NavItem dashboard1 = new NavItem("bi-circle", "/asasd", new EmptyText("Dashboard1"));
        NavItem userAdd = new NavItem("bi-house", Weboo.getPath(HomePage.class), new EmptyText("Home"));
        dashboard.add(userAdd);
        dashboard.add(dashboard1);

        getSidenav().addNavItem(dashboard);

    }
}
