package hr.ja.weboo;

import hr.ja.weboo.components.Pre;
import hr.ja.weboo.js.CustomJavaScript;
import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {

    @Getter
    private List<Widget> widgets = new ArrayList<>();

    //@Getter
    //private final String pageId = WebooUtil.pageNewId(getClass());

    @Getter
    @Setter
    private Layout layout = null;

    @Setter
    @Getter
    private String title = "Title " + getClass().getSimpleName();

//    public abstract void onRequest();

    public <T extends Widget> T add(T widget) {
        widgets.add(widget);
        return widget;
    }

    public void call(JavaScriptFunction function) {
        PageRequestContext.add(function, Context.getPageId());
    }

    public void call(String jsCode, String... args) {
        PageRequestContext.add(new CustomJavaScript(jsCode, args), Context.getPageId());
    }


    public void dump(Object u) {
        String json = WebooUtil.toJson(u);
        add(new Pre(json));

    }

}
