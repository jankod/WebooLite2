package hr.ja.weboo.js;

import hr.ja.weboo.Context;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class JsCommand {

    protected String name;

    public JsCommand() {
             name = JsUtil.createJsCommandName(getClass());
        //name = createName();
        Context.prepareCommand(this.getClass());
//        log.debug("Create name {}", name);
    }

//    protected String createName() {
//        name = JsUtil.createJsCommandName(getClass());
//        return name;
//    }


}
