package hr.ja.weboo.js;

import hr.ja.weboo.Page;
import hr.ja.weboo.Weboo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JavaScript("""
      window.location.replace(this.path);
      """)
@Getter
public class LocationReplace extends JsCommand {

    private final String path;

    public LocationReplace(Class<? extends Page> page) {
        path = Weboo.getPath(page);
    }

}
