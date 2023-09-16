package hr.ja.weboo.adminlte4;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Brand extends Widget {

        private  String text;
        private  String logoPath;
        private  String logoAlt;

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
                          <span class="brand-text fw-light">{text}</span>
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
