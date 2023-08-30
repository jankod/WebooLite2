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

    public static AjaxResult call(JavaScriptFunction... func) {
        AjaxResult ar = empty();
        ar.getFunctions().addAll(Arrays.asList(func));
        return ar;
    }

    public AjaxResult add(JavaScriptFunction commnad) {
        functions.add(commnad);
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
        return new AjaxResult().add(new LocationReplaceFunc(aClass));
    }

    public static AjaxResult alert(String message) {
        return empty().add(new AlertFunc(message));
    }
}
