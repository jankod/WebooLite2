package hr.ja.weboo.js;

import hr.ja.weboo.Context;
import hr.ja.weboo.PageSessionManager;
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
        name = JsUtil.createJsCommandName(getClass());
        //PageSessionManager.register(this.getClass());
    }
}
