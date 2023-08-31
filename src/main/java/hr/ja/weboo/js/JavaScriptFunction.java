package hr.ja.weboo.js;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * event name i present
 */
@Slf4j
@Getter
public abstract class JavaScriptFunction {

    protected String name;

    public JavaScriptFunction() {
        name = JsUtil.createJsFunctionName(getClass());
        //PageSessionManager.register(this.getClass());
    }
}
