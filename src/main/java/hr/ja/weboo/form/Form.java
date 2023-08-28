package hr.ja.weboo.form;

import hr.ja.weboo.ClientEvent;
import hr.ja.weboo.EventHandler;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;
import hr.ja.weboo.js.CustomJsCommand;
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
                   {#for c in children}
                      {c}
                   {/for}
              </form>
              <script>
              //  weboo.form('{id}');
              </script>
                """;

        return WebooUtil.qute(template, Map.of(
              "id", this.getWidgetId(),
              "children", getChildren(),
              "attr", getIdClassStyleAttr()));
    }

    public void onSubmit(SubmitHandler handler) {
        EventHandler h = () -> handler.submitForm(new FormData());

        ClientEvent clientEvent = on("submit");
        clientEvent.handleOnClient(new CustomJsCommand("""
              console.log(" this.",  this);
              this.event.preventDefault();
              this.event.stopPropagation();
              alert("form submit!");
              """, "widgetId", getWidgetId()));

        clientEvent.setServerHandler(h);
    }

    public static void main(String[] args) {
        Form form = new Form();

        System.out.println(form.toHtml());
    }

}

