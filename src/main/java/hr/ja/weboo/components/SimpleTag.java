package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class SimpleTag extends Widget {

    private final String tag;
    private String text = "";

    private final Map<String, Object> attributes = new HashMap<>();

    public SimpleTag(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public SimpleTag(String tag, Widget... widgets) {
        super(widgets);
        this.tag = tag;
    }

    public SimpleTag attr(String name, Object value) {
        attributes.put(name, Objects.toString(value, ""));
        return this;
    }

    @Override
    public String toHtml() {

        String template = """
                <{tag} {attr.raw} {attributes.raw} >
                      {text}
                      {children.raw}
                    </{tag}>
              """;

        return WebooUtil.qute(template, Map.of(
              "tag", tag,
              "text", text,
              "children", toChildrenHtml(),
              "attributes", prepareAttributes(),
              "attr", getIdClassStyleAttr()

        ));
    }

    private String prepareAttributes() {
        final StringBuilder att = new StringBuilder();
        attributes.forEach((name, o) -> {
            //MessageFormat.format("sadas {1}", "");
            String value = WebooUtil.escape(o.toString());
            att.append("""
                  %s="%s" """.formatted(name, value));
        });
        return att.toString();
    }
}
