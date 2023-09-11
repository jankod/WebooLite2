package hr.ja.weboo.adminlte4;

import hr.ja.weboo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AdminLte4Layout implements Layout {

    private List<String> javascriptFiles = new ArrayList<>();
    private List<String> cssFiles = new ArrayList<>();
    private String lastBodyHtmlChunk;


    protected Footer footer = new Footer();
    protected Sidenav sidenav = new Sidenav();
    protected Topbar topbar = new Topbar();
    protected Head head = new Head("");

    @Override
    public void setLastBodyHtmlChunk(String lastBodyHtmlChunk) {
        this.lastBodyHtmlChunk = lastBodyHtmlChunk;
    }

    @Override
    public String renderPage(Page page) {

        javascriptFiles.add("/weboo/jquery-3.7.0.min.js");
        javascriptFiles.add("/weboo/weboo.js");
        javascriptFiles.add("/weboo/adminlte4/js/adminlte.js");

        head.setTitle(page.getTitle());

        Breadcrumb breadcrumb = new Breadcrumb();


        String body = WidgetUtil.pageToHtml(page);


        //language=HTML
        String temp = """
              <!DOCTYPE html>
              <html lang="{lang}">
              <head>
                  {head}
                    <script>
                    const WEBOO_PAGE_ID = "{pageId}";
                  </script>
                   {#for file in webooJavascriptFile}
                        <script src='{file}'></script>
                   {/for}
              </head>
              <body class="layout-fixed sidebar-expand-lg bg-body-tertiary">
                            
              <div class="app-wrapper">
                  {topbar}
                  {sidenav}
                  
                  <!--begin::App Main-->
                  <main class="app-main">
                      <!--begin::App Content Header-->
                      <div class="app-content-header">
                          <!--begin::Container-->
                          <div class="container-fluid">
                              <!--begin::Row-->
                              <div class="row">
                                  <div class="col-sm-6">
                                      <h3 class="mb-0">Dashboard</h3>
                                  </div>
                                  <div class="col-sm-6">
                                        {breadcrumb}
                                  </div>
                              </div>
                              <!--end::Row-->
                          </div>
                          <!--end::Container-->
                      </div>
                      <!--end::App Content Header-->
                      <!--begin::App Content-->
                      <div class="app-content">
                          <!--begin::Container-->
                          <div class="container-fluid">
                             {body.raw}
                            {lastBodyTag.raw}
                          </div>
                          <!--end::App Content-->
                  </main>
                  <!--end::App Main-->
                  {footer}
                  
                       
              </div>
              <!--end::App Wrapper-->
                            
                            
                            
                            
              <!--begin::Third Party Plugin(OverlayScrollbars)-->
              <script
                src="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.1.0/browser/overlayscrollbars.browser.es6.min.js"
                integrity="sha256-NRZchBuHZWSXldqrtAOeCZpucH/1n1ToJ3C8mSK95NU="
                crossorigin="anonymous"
              ></script>
              <!--end::Third Party Plugin(OverlayScrollbars)--><!--begin::Required Plugin(popperjs for Bootstrap 5)-->
              <script
                src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
                integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
                crossorigin="anonymous"
              ></script>
              <!--end::Required Plugin(popperjs for Bootstrap 5)--><!--begin::Required Plugin(Bootstrap 5)-->
              <script
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
                integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
                crossorigin="anonymous"
              ></script>
              <!--end::Required Plugin(Bootstrap 5)--><!--begin::Required Plugin(AdminLTE)-->
                            
              <!--end::Required Plugin(AdminLTE)--><!--begin::OverlayScrollbars Configure-->
              <script is:inline>
                const SELECTOR_SIDEBAR_WRAPPER = ".sidebar-wrapper";
                const Default = {
                  scrollbarTheme: "os-theme-light",
                  scrollbarAutoHide: "leave",
                  scrollbarClickScroll: true,
                };
                            
                document.addEventListener("DOMContentLoaded", function () {
                  const sidebarWrapper = document.querySelector(SELECTOR_SIDEBAR_WRAPPER);
                  if (
                    sidebarWrapper &&
                    typeof OverlayScrollbarsGlobal?.OverlayScrollbars !== "undefined"
                  ) {
                    OverlayScrollbarsGlobal.OverlayScrollbars(sidebarWrapper, {
                      scrollbars: {
                        theme: Default.scrollbarTheme,
                        autoHide: Default.scrollbarAutoHide,
                        clickScroll: Default.scrollbarClickScroll,
                      },
                    });
                  }
                });
              </script>
              <!--end::OverlayScrollbars Configure-->
                            
                            
                            
              </body>
              </html>
              """;

        return WebooUtil.qute(temp, Map.of(
              "head", head,
              "topbar", topbar,
              "breadcrumb", breadcrumb,
              "pageId", Context.getPageId(),
              "footer", footer,
              "body", body,
              "lang", "en", // TODO: from config or???,
              "lastBodyTag", lastBodyHtmlChunk,
              "webooJavascriptFile", javascriptFiles,
              "sidenav", sidenav
        ));

    }
}
