package hr.ja.weboo.form;

import hr.ja.weboo.ClientServerEvent;
import hr.ja.weboo.ServerHandler;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import hr.ja.weboo.js.CustomJavaScript;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
public class Form extends Widget {
    private String eventId;

    @Override
    public String toHtml() {
        // language=HTML
        String template = """
              <form {attr.raw} method='post' >
                {children.raw}
              </form>
              <script>
              weboo.form('{id}');
              </script>
                """;

        return WebooUtil.qute(template, Map.of(
              "id", this.getWidgetId(),
              "children",toChildrenHtml(),
              "attr", getIdClassStyleAttr()));
    }

    public void onSubmit(SubmitHandler handler) {
        ClientServerEvent clientEvent = on("submit");
        clientEvent.handleOnClient(new CustomJavaScript("""
              console.log(" this.",  this);
              this.event.preventDefault();
              this.event.stopPropagation();
              alert("form submit!");
              """, "widgetId", getWidgetId()));

        clientEvent.setServerHandler(handler::submitForm);
    }

    public FormConfigurer configure() {
        // TODO: like spring security
        return new FormConfigurer(this);
    }

    public static void main(String[] args) {
        Form form = new Form();

        System.out.println(form.toHtml());
    }

}

