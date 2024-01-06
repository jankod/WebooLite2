package hr.ja.weboo.adminlte4;

import hr.ja.weboo.EmptyText;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Sidenav extends Widget {

    protected Brand brand = new Brand();

    private List<NavItem> navItems = new ArrayList<>();



    @Override
    public String toHtml() {


        //language=HTML
        String t = """
              <!--begin::Sidebar-->
              <aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
                    {brand}
                  <!--begin::Sidebar Wrapper-->
                  <div class="sidebar-wrapper">
                      <nav class="mt-2">
                          <!--begin::Sidebar Menu-->
                          <ul
                                  class="nav sidebar-menu flex-column"
                                  data-lte-toggle="treeview"
                                  role="menu"
                                  data-accordion="false">
                                  
                           
                           {#for item in navItems}
                                {item}
                           {/for}
                          </ul>
                          <!--end::Sidebar Menu-->
                      </nav>
                  </div>
                  <!--end::Sidebar Wrapper-->
              </aside>
              <!--end::Sidebar-->
              """;
        return WebooUtil.qute(t, Map.of(
              "brand", brand,
              "navItems", navItems
        ));
    }

    public void addNavItem(NavItem navItem) {
        navItems.add(navItem);
    }
}
