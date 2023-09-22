package hr.ja.weboo.js;

import hr.ja.weboo.Page;
import hr.ja.weboo.Weboo;
import lombok.Getter;

@JavaScript("""
      window.location.replace(this.path);
      """)
@Getter
public class GoToPageFunction extends JavaScriptFunction {

    private final String path;

    public GoToPageFunction(Class<? extends Page> page) {
        path = Weboo.getPath(page);
    }

}
