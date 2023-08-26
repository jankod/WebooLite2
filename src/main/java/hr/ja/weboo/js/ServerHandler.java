package hr.ja.weboo.js;

import hr.ja.weboo.EventHandler;
import hr.ja.weboo.WebooUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ServerHandler {

    private Map<String, HandlerWrapper> handlerMap = new HashMap<>();

    public String register(EventHandler serverHandler, String pageId, String widgetId) {
        String handlerId = WebooUtil.eventHandlerNewId(serverHandler.getClass());
        handlerMap.put(handlerId, new HandlerWrapper(serverHandler, handlerId, pageId, widgetId));
        return handlerId;
    }

    public static EventHandler get(String handlerId, String widgetId, String pageId) {
        if (!handlerMap.containsKey(handlerId)) {
            return null;
        }

        HandlerWrapper handlerWrapper = handlerMap.get(handlerId);
        if (handlerWrapper.isSame(widgetId, pageId)) {
            return handlerWrapper.eventHandler;
        }

        return null;
    }
}

@Data
@AllArgsConstructor
class HandlerWrapper {
    EventHandler eventHandler;
    String handlerId;
    String pageId;
    String widgetId;

    public boolean isSame(String widgetId, String pageId) {
        if (this.widgetId.equals(widgetId) && this.pageId.equals(pageId)) {
            return true;
        }
        return false;
    }
}
