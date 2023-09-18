package hr.ja.weboo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@UtilityClass
public class PageStaticContext {

    /**
     * handlerId => HandlerWrapper
     */
    private Map<String, HandlerWrapper> handlerMap = new HashMap<>();

    public String register(hr.ja.weboo.ServerHandler serverHandler, String pageId, String widgetId) {
        String handlerId = WebooUtil.eventHandlerNewId(serverHandler.getClass());
        String sessionId = Context.req().session(true).id();

        handlerMap.put(handlerId, new HandlerWrapper(serverHandler, handlerId, pageId, widgetId, sessionId));
        return handlerId;
    }

    public static hr.ja.weboo.ServerHandler get(String handlerId, String widgetId, String pageId, String sessionId) {
        if (!handlerMap.containsKey(handlerId)) {
            return null;
        }

        HandlerWrapper handlerWrapper = handlerMap.get(handlerId);
        if (handlerWrapper.isSame(widgetId, pageId)) {
            if (handlerWrapper.sessionId.equals(sessionId))
                return handlerWrapper.eventHandler;
        }

        log.warn("Not find server handler for handlerId {} widgetId {} pageId {} sessionId {}", handlerId, widgetId, pageId, sessionId);
        return null;
    }

    public static void pageClosed(String pageId, String sessionId) {
        for (HandlerWrapper h : handlerMap.values()) {
            if (h.pageId.equals(pageId) && h.sessionId.equals(sessionId)) {
                log.debug("Remove handler {} of pageId {}", h.handlerId, pageId);
                handlerMap.remove(h.handlerId);
            }
        }
    }

}

@Data
@AllArgsConstructor
class HandlerWrapper {
    hr.ja.weboo.ServerHandler eventHandler;
    String handlerId;
    String pageId;
    String widgetId;
    String sessionId;

    public boolean isSame(String widgetId, String pageId) {
        return this.widgetId.equals(widgetId) && this.pageId.equals(pageId);
    }
}
