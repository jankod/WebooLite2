package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import hr.ja.weboo.components.bs.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class SubmitButton extends Widget {

    private final String label;

    @Getter
    @Setter
    private Color color = Color.PRIMARY;

    public SubmitButton(String label) {
        this.label = label;
        setClasses("btn");
    }

    @Override
    public String toHtml() {
        addClass(color.toCssClass());

        String t = """
              <button {attr.raw} type='submit'>{label}</button>
              """;

        return WebooUtil.qute(t, Map.of(
              "attr", getIdClassStyleAttr(),
              "label", label
        ));
    }

}
