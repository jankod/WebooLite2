package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Button extends Widget {

    private String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml() {

        String t = """
              <button {idClassAttr.raw} >{label}</button>
              """;

        return WebooUtil.qute(t, Map.of(
              "idClassAttr", this.getIdClassStyleAttr(),
              "label", getLabel()
              //"handlerId", "????", // TODO: add real id
        ));

    }
}
