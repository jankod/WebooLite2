package hr.ja.demo;

import hr.ja.weboo.*;
import hr.ja.weboo.components.Button;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.components.Link;
import hr.ja.weboo.js.*;
import lombok.extern.slf4j.Slf4j;

@Path("/")
@Slf4j
public class HomePage extends Page {

    private Button btn = new Button("Klikni me user list page");
    {
        btn.on("ssfd").handleOnClient("");
    }

    public HomePage() {
        setTitle("Main page");

        btn.on("click")
              .handleOnClient("""
                    console.log("Custom code param1 = ", this.param1 + " param2 ="+ this.param2);
                                          
                    return {
                          "data1": "value11"
                    }
                     
                      """, "param1", "value1", "param2", "value2")

              .handleOnServer(() -> {

                  WebooRequest req = Context.req();

                  String data1 = req.queryParams("data1");

                  log.debug("Got data1 {}", data1);

                  return new AjaxResult().goTo(UserListPage.class);

              });

        add(new H3("Main page"));
        add(new Link("User add", UserAddPage.class));
        add(btn);

        call(new AlertFunc("Ovo je page"));
    }


}
