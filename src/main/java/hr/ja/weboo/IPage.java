package hr.ja.weboo;

import hr.ja.weboo.js.CustomJavaScript;
import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Getter;

public interface IPage {

    default void setTitle(String title) {
        PageRequestContext.setTitle(title);
    }

    default void add(Widget... widgets) {
        PageRequestContext.add(widgets);
    }


    default void call(JavaScriptFunction function) {
        PageRequestContext.add(function, Context.getPageId());
    }

    default void call(String jsCode, String... args) {
        PageRequestContext.add(new CustomJavaScript(jsCode, args), Context.getPageId());
    }
}
