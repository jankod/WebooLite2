package hr.ja.weboo.js;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * js name create
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
