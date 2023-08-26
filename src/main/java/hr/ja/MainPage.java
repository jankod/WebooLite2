package hr.ja;

import hr.ja.weboo.*;
import hr.ja.weboo.components.Button;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.js.AjaxResult;
import hr.ja.weboo.js.AlertCommand;
import hr.ja.weboo.js.CustomCodeCommand;
import hr.ja.weboo.js.JsCommand;
import lombok.extern.slf4j.Slf4j;

@Path("/")
@Slf4j
public class MainPage extends Page {

    Button btn = new Button("Klikni me");

    public MainPage() {
        setTitle("Main page");
        setLayout(new DefaultLayout());
        register(AlertCommand.class);

        btn.on("click")
              .handleOnClient(new CustomCodeCommand("""
                  //  console.log("Custom code this.args = ", this.param1 + " param2 ="+ this.param2);
                                        
                    return {
                        "data1": "value11"
                    }
                    """, "param1", "value1", "param2", "value2"))

              .handleOnServer(() -> {
                  WebooRequest req = Context.req();

                  String data1 = req.params("data1");

                  log.debug("Got data1 {}", data1);

                  return AjaxResult.command(new AlertCommand("Dobio ovo, data1 " + data1));


              });

        add(new H3("Main page"));
        add(btn);
    }

    private void register(Class<? extends JsCommand> commandClass) {
        Context.register(commandClass);
    }


}
