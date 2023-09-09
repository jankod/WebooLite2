package hr.ja.weboo.adminlte4;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

import java.util.Map;

public class Head extends Widget {

    private final String title;

    public Head(String title) {
        this.title = title;
    }

    @Override
    public String toHtml() {


        String temp = """
              <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
              <title>{title}</title>
              <meta name="viewport" content="width=device-width, initial-scale=1.0" />
              <meta name="title" content={title} />
              
              <!--begin::Fonts-->
              <link rel="preconnect" href="https://fonts.googleapis.com" />
              <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
              
              <link
                href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:ital,wght@0,300;0,400;0,700;1,400&display=swap"
                rel="stylesheet"
              />
              <!--end::Fonts-->
              <!--begin::Third Party Plugin(OverlayScrollbars)-->
              <link
                rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.1.0/styles/overlayscrollbars.min.css"
                integrity="sha256-LWLZPJ7X1jJLI5OG5695qDemW1qQ7lNdbTfQ64ylbUY="
                crossorigin="anonymous"
              />
              <!--end::Third Party Plugin(OverlayScrollbars)-->
              <!--begin::Third Party Plugin(Bootstrap Icons)-->
              <link
                rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.min.css"
                integrity="sha256-BicZsQAhkGHIoR//IB2amPN5SrRb3fHB8tFsnqRAwnk="
                crossorigin="anonymous"
              >
              <!--end::Third Party Plugin(Bootstrap Icons)-->
              <!--begin::Required Plugin(AdminLTE)-->
              <link rel="stylesheet" href="/weboo/adminlte4/css/adminlte.css" />
              <!--end::Required Plugin(AdminLTE)-->
              """;
        return WebooUtil.qute(temp, Map.of(
              "title", title));
    }
}
