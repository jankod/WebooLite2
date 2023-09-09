package hr.ja.weboo.adminlte4;

import hr.ja.weboo.Widget;

public class Breadcrumb extends Widget {
    @Override
    public String toHtml() {
        return """
                     <ol class="breadcrumb float-sm-end">
                                          <li class="breadcrumb-item"><a href="#">Home</a></li>
                                          <li class="breadcrumb-item active" aria-current="page">
                                              Dashboard
                                          </li>
                                      </ol>
              
              """;
    }
}
