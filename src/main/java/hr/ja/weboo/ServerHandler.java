package hr.ja.weboo;

import hr.ja.weboo.js.AjaxResult;

@FunctionalInterface
public interface ServerHandler {

    AjaxResult handle();

}
