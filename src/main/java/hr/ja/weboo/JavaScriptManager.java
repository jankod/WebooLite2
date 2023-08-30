package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class JavaScriptManager {

    private final Set<Class<? extends JavaScriptFunction>> functions = new HashSet<>();
    public static void add(Class<? extends JavaScriptFunction> functionClass) {

    }
}
