package hr.ja.weboo.adminlte4;

import hr.ja.weboo.Widget;

public class Footer extends Widget {

    @Override
    public String toHtml() {
        return """
              <footer class="app-footer">
                <div class="float-end d-none d-sm-inline">Anything you want</div>
                <strong>
                  Copyright &copy; 2014-{year}
                  <a href="https://adminlte.io">AdminLTE.io</a>.
                </strong>
                All rights reserved.
              </footer>
              """;
    }
}
