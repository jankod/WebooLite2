package hr.ja.weboo.js;

import hr.ja.weboo.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AjaxResult implements Serializable {

    private final List<String> errors = new ArrayList<>();

    private final List<JsCommand> commands = new ArrayList<>();

    public static AjaxResult command(JsCommand command) {
        return empty().addCommand(command);
    }

    public AjaxResult addCommand(JsCommand commnad) {
        commands.add(commnad);
        return this;
    }

    public static AjaxResult empty() {
        return new AjaxResult();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }


    public static AjaxResult goTo(Class<? extends Page> aClass) {
        return new AjaxResult().addCommand(new LocationReplace(aClass));
    }

    public static AjaxResult alert(String message) {
        return empty().addCommand(new AlertCommand(message));
    }
}
