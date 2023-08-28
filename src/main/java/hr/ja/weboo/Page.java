package hr.ja.weboo;

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
    private Layout layout = new DefaultLayout();

    @Setter
    @Getter
    private String title = "Title " + getClass().getSimpleName();

//    public abstract void onRequest();

    public <T extends Widget> T add(T widget) {
        widgets.add(widget);
        return widget;
    }
}
