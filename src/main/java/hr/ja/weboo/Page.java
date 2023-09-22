package hr.ja.weboo;

import hr.ja.weboo.components.Pre;
import hr.ja.weboo.js.CustomJavaScript;
import hr.ja.weboo.js.JavaScriptFunction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {

    @Getter
    private List<Widget> widgets = new ArrayList<>();

    @Getter
    @Setter
    private Layout layout = null;

    @Setter
    @Getter
    private String title = "Title " + getClass().getSimpleName();

    @Getter(AccessLevel.PACKAGE)
    private final List<JavaScriptFunction> javaScript = new ArrayList<>();

//    public abstract void onRequest();

    public <T extends Widget> T add(T widget) {
        widgets.add(widget);
        return widget;
    }

    public void call(JavaScriptFunction function) {
        javaScript.add(function);
    }

    public void call(String jsCode, String... args) {
        javaScript.add(new CustomJavaScript(jsCode, args));
    }

    public void dump(Object u) {
        String json = WebooUtil.toJson(u);
        add(new Pre(json));
    }

}
