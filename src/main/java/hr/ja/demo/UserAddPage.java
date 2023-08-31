package hr.ja.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import hr.ja.demo.model.Role;
import hr.ja.demo.model.User;
import hr.ja.weboo.*;
import hr.ja.weboo.components.AlertWidget;
import hr.ja.weboo.components.H3;
import hr.ja.weboo.components.Link;
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
        form.add(new TextField(User.Fields.name, "Name"));
        Select selectRole = new Select(User.Fields.role, "Role");
        AlertWidget message = new AlertWidget("");

        selectRole.addOption("null", "Select one")
              .setSelected(true)
              .setDisable(true);
        selectRole.addOption(Role.ADMIN, "Admin");
        selectRole.addOption(Role.USER, "User");

        form.add(selectRole);

        form.add(new SubmitButton("Save new user"));

        form.on("submit")
              .handleOnClient(new SendFormFunction(form))
              .handleOnServer(() -> {
                  try {
                      User user = Context.req().bindJsonTo(User.class);
                      log.debug("Got user {}", user);

                      Set<ConstraintViolation<User>> violations = WebooUtil.validate(user);

                      if (violations.isEmpty()) {
                          user.save();

                          return new AjaxResult()
                                .alert("Jeee")
                                .call(message.callShowMessage("Uspjesno si unio!"))
                                //.goTo(UserAddPage.class);
                          ;
                      } else {
                          return new AjaxResult().call(new ShowValidationErrorsFunction(violations, form.getWidgetId()));
                      }

                  } catch (JsonProcessingException e) {
                      return new AjaxResult().call(new AlertFunc("Error json: " + e.getMessage()));
                  }
              });

        add(new H3("Add user"));
        add(message);
        add(form);

        add(new Link("List user", UserListPage.class));
    }
}
