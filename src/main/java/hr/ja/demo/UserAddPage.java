package hr.ja.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import hr.ja.weboo.*;
import hr.ja.weboo.ServerHandler;
import hr.ja.weboo.components.AlertWidget;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.components.SubmitButton;
import hr.ja.weboo.form.*;
import hr.ja.weboo.js.*;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@Path("/users/add")
public class UserAddPage extends Page {

    public UserAddPage() {
        Form form = new Form();
        AlertWidget alertWidget = new AlertWidget();
        form.add(alertWidget);
        form.add(new TextField(User.Fields.name, "Name"));

        form.add(new SubmitButton("Save"));

        form.on("submit")
              .handleOnClient(new FormSubmitFunction(form))
              .handleOnServer(() -> {
                  try {
                      User user = Context.req().bindJsonTo(User.class);

                      Set<ConstraintViolation<User>> violations = WebooUtil.validate(user);

                      if (violations.isEmpty()) {
                          return AjaxResult.call(new AlertCommand("Succesfull submitet form!"));
                      } else {
                          return AjaxResult.call(WebooJs.showValidationErrors(violations, form.getWidgetId()));
                      }

                  } catch (JsonProcessingException e) {
                      return AjaxResult.call(new AlertCommand("Error json " + e.getMessage()));
                  }
                  // validate and return

              });

        form.on("submit")
              .handleOnClient(new CustomJavaScript("""
                       console.log("This {}", this);
                       
                    """)).
              handleOnServer(new ServerHandler() {
                  @Override
                  public AjaxResult handle() {
                      return AjaxResult.call(new AlertCommand("dela"));
                  }
              });


        add(new H3("Add user"));
        add(form);
    }
}
