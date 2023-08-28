package hr.ja.weboo.js;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JavaScript("""
    alert(this.message);
""")
@RequiredArgsConstructor
@Getter
public class AlertCommand extends JsCommand{

    @JavaScriptParam
    private final String message;
}
