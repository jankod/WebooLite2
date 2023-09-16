package hr.ja.weboo.js;

import hr.ja.weboo.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsUtil {

    public static void checkCommand(Class<? extends JavaScriptFunction> c) {
        if (c.getAnnotation(JavaScript.class) == null) {
            throw new RuntimeException("JsCommand " + c + " does not have JavaScript annotation.");
        }
    }

    public static String createJsEventsCallCode(List<ClientServerEvent> clientServerEvents) {
        StringBuilder js = new StringBuilder();

        for (ClientServerEvent e : clientServerEvents) {
            JavaScriptFunction function = e.getJsFunction();

            String eventName = e.getEventName();
            String widgetId = e.getWidgetId();
            String handlerId = ServerHandlerManager.register(e.getServerHandler(), Context.getPageId(), widgetId);
            String funcCall = WebooUtil.toJson(function);

            //WebooJs.onEvent(widgetId, eventName, handlerId, jsCommand);

            String template = """
                  weboo.onEvent("{widgetId}", "{eventName}", "{handlerId}", {funcCall.raw});
                  """;
            js.append(WebooUtil.qute(template,
                  Map.of(
                        "widgetId", widgetId,
                        "eventName", eventName,
                        "funcCall", funcCall,
                        "handlerId", handlerId)));
        }


        return js.toString();

    }


    public static String createJsFunctionName(Class<? extends JavaScriptFunction> aClass) {
        return aClass.getName().replaceAll("[^a-zA-Z0-9_$]", "_");
    }

    public static String createJsFunctionDefinitionCode(Class<? extends JavaScriptFunction> c) {
        JsUtil.checkCommand(c);
        String code = c.getAnnotation(JavaScript.class).value();
        String functionName = createJsFunctionName(c);
        //String parameters = String.join(", ", findJsParameters(c));

        String template = """
              weboo.functions.{functionName} = function() {
                {code.raw}
              }
                                    
              """;
        return WebooUtil.qute(template, Map.of("functionName", functionName, "code", code));
    }

    private static List<String> findJsParameters(Class<?> aClass) {
        List<String> fieldsWithAnnotation = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(JavaScriptParam.class)) {
                fieldsWithAnnotation.add(field.getName());
            }
        }
        return fieldsWithAnnotation;
    }

    public static String createJsFunctionDefinitionCode(Collection<Class<? extends JavaScriptFunction>> commandDefinitions) {
        StringBuilder js = new StringBuilder();
        for (Class<? extends JavaScriptFunction> c : commandDefinitions) {
            js.append(createJsFunctionDefinitionCode(c));
        }
        return js.toString();
    }
}
