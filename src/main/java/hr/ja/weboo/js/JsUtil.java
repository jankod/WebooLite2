package hr.ja.weboo.js;

import hr.ja.weboo.ClientEvent;
import hr.ja.weboo.Context;
import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsUtil {

    public static void checkCommand(Class<? extends JsCommand> c) {
        if (c.getAnnotation(JavaScript.class) == null) {
            throw new RuntimeException("JsCommand " + c + " does not have JavaScript annotation");
        }
    }

    public static String createJsEventsCode(List<Widget> widgets) {
        StringBuilder js = new StringBuilder();

        for (Widget widget : widgets) {
            List<ClientEvent> clientEvents = widget.getClientEvents();
            for (ClientEvent e : clientEvents) {
                String eventName = e.getEventName();
                String widgetId = e.getWidgetId();
                JsCommand jsCommand = e.getCommand();

                String handlerId = ServerHandler.register(e.getServerHandler(), Context.getPageId(), widgetId);

                String jsonCommandData = WebooUtil.toJson(jsCommand);

                //WebooJs.onEvent(widgetId, eventName, handlerId, jsCommand);

                String template = """
                      weboo.onEvent("{widgetId}", "{eventName}", "{handlerId}", {jsonCommandData.raw});
                      """;
                js.append(WebooUtil.qute(template,
                      Map.of(
                            "widgetId", widgetId,
                            "eventName", eventName,
                            "jsonCommandData", jsonCommandData,
                            "handlerId", handlerId)));
            }

            if (widget.hasChildren()) {
                List<Widget> children = widget.getChildren();
                createJsEventsCode(children);
            }

        }
        return js.toString();

    }


    public static String createJsCommandName(Class<? extends JsCommand> aClass) {

        return aClass.getName().replaceAll("[^a-zA-Z0-9_$]", "_");
    }

    public static String createJsCommandCodeDefinition(Class<? extends JsCommand> c) {
        JsUtil.checkCommand(c);
        String code = c.getAnnotation(JavaScript.class).value();
        String commandName = createJsCommandName(c);
        String parameters = String.join(", ", findJsParameters(c));

        String template = """
              weboo.commands.{commandName} = function({parameters}) {
                {code.raw}
              }
                                    
              """;
        return WebooUtil.qute(template, Map.of("commandName", commandName, "parameters", parameters, "code", code));
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

    public static String createJsCommandCodeDefinition(Collection<Class<? extends JsCommand>> commandDefinitions) {
        StringBuilder js = new StringBuilder();
        for (Class<? extends JsCommand> c : commandDefinitions) {
            js.append(createJsCommandCodeDefinition(c));
        }
        return js.toString();
    }
}
