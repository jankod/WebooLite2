package hr.ja.weboo.components;

import hr.ja.weboo.Page;
import hr.ja.weboo.Weboo;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

import java.util.Map;

public class Link extends Widget {

    private String label;
    private String url;

    public Link(String label, Class<? extends Page> page) {
        this.label = label;
        url = Weboo.getPath(page);
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
