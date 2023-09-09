package hr.ja.weboo.adminlte4;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sidenav extends Widget {

    class NavMenu extends Widget {

        List<NavItem> navItems = new ArrayList<>();

        @Override
        public String toHtml() {
            String t = """
                                    
                  """;
            return WebooUtil.qute(t, Map.of(
                  "", ""));
        }
    }


    class Brand extends Widget {

        private final String text;
        private final String logoPath;
        private final String logoAlt;

        public Brand(String text, String logoPath, String logoAlt) {

            this.text = text;
            this.logoPath = logoPath;
            this.logoAlt = logoAlt;
        }

        public Brand() {
            this.text = "AdminLTE 4";
            this.logoAlt = "AdminLTE Logo";
            this.logoPath = "/weboo/adminlte4/assets/img/AdminLTELogo.png";
        }

        @Override
        public String toHtml() {
            String t = """
                    <div class="sidebar-brand">
                      <a href="#" class="brand-link">
                          <img src="{logoPath}"
                                  alt="{logoAlt}"
                                  class="brand-image opacity-75 shadow"
                          />
                          <span class="brand-text fw-light">{logoAlt}</span>
                      </a>
                  </div>
                  """;
            return WebooUtil.qute(t, Map.of(
                  "text", text,
                  "logoPath", logoPath,
                  "logoAlt", logoAlt
            ));
        }
    }




    private List<NavItem> navItems = new ArrayList<>();

    @Override
    public String toHtml() {
        Brand brand = new Brand();
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
                                  
                              <li class="nav-item menu-open">
                              <a href="#" class="nav-link active" >
                              <i class="nav-icon bi bi-speedometer"></i>
                              <p>
                                  Dashboard
                                  <i class="nav-arrow bi bi-chevron-right"></i>
                              </p>
                              </a>
                              
                              
                              <ul class="nav nav-treeview">
                                  <li class="nav-item">
                                      <a href="/index3.html"  class="nav-link active"  >
                                      <i class="nav-icon bi bi-circle"></i>
                                      <p>Dashboard v3</p>
                                      </a>
                                  </li>
                              </ul>
                          </ul>
                          <!--end::Sidebar Menu-->
                      </nav>
                  </div>
                  <!--end::Sidebar Wrapper-->
              </aside>
              <!--end::Sidebar-->
              """;
        return WebooUtil.qute(t, Map.of(
              "brand", brand
        ));
    }
}
