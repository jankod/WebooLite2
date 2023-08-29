package hr.ja.weboo.js;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JavaScript("""
    alert(this.message);
""")
@RequiredArgsConstructor
@Getter
public class AlertCommand extends JavaScriptFunction {

    @JavaScriptParam
    private final String message;
}
