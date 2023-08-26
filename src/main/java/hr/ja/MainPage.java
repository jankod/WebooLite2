package hr.ja;

import hr.ja.weboo.*;
import hr.ja.weboo.components.Button;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.js.CustomCodeCommand;
import hr.ja.weboo.js.OnEventCommand;
import lombok.extern.slf4j.Slf4j;

@Path("/")
@Slf4j
public class MainPage extends Page {

    Button btn = new Button("Klikni me");

    public MainPage() {
        setTitle("Main page");
        setLayout(new DefaultLayout());

        btn.on("click")
              .handleOnClient(new CustomCodeCommand("""
                    console.log("Custom code this.args = ", this.param1 + " param2 ="+ this.param2);
                    
                    return {
                        "param11": "value11"
                    }
                    """, "param1", "value1", "param2", "value2"))

              .handleOnServer(() -> {
                  WebooRequest req = Context.req();
                  req.params("data1");

                  //Context.sendCommand(new OnEventCommand(""));
                  //Context.res().comand(new )

              });

        add(new H3("Main page"));
        add(btn);
    }


}
