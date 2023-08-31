package hr.ja.weboo.components;

import hr.ja.demo.UserEditPage;
import hr.ja.weboo.Page;
import hr.ja.weboo.Weboo;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Link extends Widget {

    private String label;
    private String url;

    public Link(String label, Class<? extends Page> page, Object... queryParams) {
        this.label = requireNonNull(label);
        this.url = requireNonNull(Weboo.getPath(page));
        if (!ArrayUtils.isEmpty(queryParams)) {
            url += "?";
            for (int i = 0; i < queryParams.length; i++) {
                if (i % 2 == 0) {
                    url += queryParams[i];
                } else {
                    url += "=" + queryParams[i]+"&";
                }
            }
        }
    }

    @Override
    public String toHtml() {
        String template = """
              <a href="${url}" class='btn btn-link'>${label}</a>
              """;

        return WebooUtil.quteThis(template, Map.of(
              "url", url, "label", label
        ));
    }
}
