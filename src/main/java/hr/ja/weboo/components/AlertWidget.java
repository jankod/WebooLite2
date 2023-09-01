package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import hr.ja.weboo.components.bs.Color;
import hr.ja.weboo.js.CustomJavaScript;
import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class AlertWidget extends Widget {

    private final String message;

    @Setter
    private Color color;

    public AlertWidget(String message, Color color) {
        this.message = message;
        this.color = color;
        addClass("alert alert-dismissible fade show alert-" + color.toName());
        add(new HtmlWidget(message));

    }

    @Override
    public String toHtml() {

        String template = """
              <div {attr.raw} role="alert">
               <span id='message_{id}'>
                {children.raw}
                </span>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
              """;

        return WebooUtil.qute(template, Map.of(
              "attr", getIdClassStyleAttr(),
              "children", toChildrenHtml(),
              "id", getWidgetId()
        ));
    }

    public JavaScriptFunction callShowMessage(String message) {
        return new CustomJavaScript("""
                  $("#message_"+this.id).html(this.message);
              """, "message", message, "id", getWidgetId());
    }
}
