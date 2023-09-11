package hr.ja.weboo.adminlte4;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

import java.util.Map;

public class Brand extends Widget {

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
