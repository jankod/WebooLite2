package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;

import java.util.Collection;

public interface Layout {

    void setLastBodyHtmlChunk(String html);

    String renderPage(Page page);

    Collection<Class<? extends JavaScriptFunction>> getJavaScript();
}
