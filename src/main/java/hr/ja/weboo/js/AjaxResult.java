package hr.ja.weboo.js;

import hr.ja.weboo.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class AjaxResult implements Serializable {

    private final List<String> errors = new ArrayList<>();

    private final List<JavaScriptFunction> functions = new ArrayList<>();

    public AjaxResult call(JavaScriptFunction... func) {
        getFunctions().addAll(Arrays.asList(func));
        return this;
    }

    public AjaxResult add(JavaScriptFunction commnad) {
        functions.add(commnad);
        return this;
    }


    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }


    public AjaxResult goTo(Class<? extends Page> aClass) {
        return add(new GoToPageFunction(aClass));
    }

    public AjaxResult alert(String message) {
        return add(new AlertFunction(message));
    }
}
