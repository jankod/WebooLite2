package hr.ja.weboo.js;


import hr.ja.weboo.components.Button;

import java.util.Arrays;
import java.util.Collections;

class JsUtilTest {

    void checkCommand() {
    }

    void createJsEventsCode() {

    }

    void createJsCommandName() {
    }

    void createJsCommandCodeDefinition() {

        //JsUtil.createJsCommandCodeDefinition();
    }

    void testCreateJsCommandCodeDefinition() {
    }

    public static void main(String[] args) {
        Button b = new Button("pero");


        String jsEventsCode = JsUtil.createJsEventsCode(Arrays.asList(b));
        System.out.println(jsEventsCode);

    }
}
