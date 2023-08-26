package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Button extends Component {

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
              "idClassAttr", this.getIdClassAttr(),
              "label", getLabel()
              //"handlerId", "????", // TODO: add real id
        ));

    }
}
