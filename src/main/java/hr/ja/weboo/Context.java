package hr.ja.weboo;

import hr.ja.weboo.js.JsCommand;
import lombok.Data;
import lombok.experimental.UtilityClass;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class Context {

    private final ThreadLocal<CurrentContext> contextHolder = new ThreadLocal<>();

    void setCurrentContext(Request request, Response response, String pageId, PageMeta page) {
        CurrentContext value = new CurrentContext(request, response, pageId, page);
        contextHolder.set(value);
    }

    public WebooRequest req() {
        return new WebooRequest(contextHolder.get().getSparkRequest());
    }

    public WebooResponse res() {
        return new WebooResponse(contextHolder.get().getSparkResponse());
    }

    public String getPageId() {
        CurrentContext myRequest = contextHolder.get();
        if (myRequest == null) {
            return null;
        }
        return myRequest.getPageId();
    }

    void removeContext() {
        contextHolder.remove();
    }

    CurrentContext getCurrentContext() {
        return contextHolder.get();
    }


    public Class<? extends Page> getCurrentPage() {
        return contextHolder.get().pageMeta.getPageClass();
    }

    public static void sendCommand(JsCommand jsCommand) {

    }

    public static void register(Class<? extends JsCommand> commandClass) {
        CurrentContext context = contextHolder.get();
        if(context != null) {
            context.getCommandDefinitions().add(commandClass);
        }
    }


    @Data
    static final class CurrentContext {

        private final Request sparkRequest;
        private final Response sparkResponse;
        private final String pageId;
        private final PageMeta pageMeta;

        private final Set<Class<? extends JsCommand>> commandDefinitions = new HashSet<>();

        private final List<JsCommand> commandForSend = new ArrayList<>();


        CurrentContext(Request sparkRequest, Response sparkResponse, String pageId, PageMeta pageMeta) {
            this.sparkRequest = sparkRequest;
            this.sparkResponse = sparkResponse;
            this.pageId = pageId;
            this.pageMeta = pageMeta;
        }


    }
}
