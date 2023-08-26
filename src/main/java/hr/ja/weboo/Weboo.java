package hr.ja.weboo;

import hr.ja.weboo.js.JsUtil;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import spark.Spark;

import java.util.UUID;

import static spark.Spark.post;
import static spark.Spark.staticFiles;

@Slf4j
@UtilityClass
@Getter
public class Weboo {

    private static PageManager pageManager = new PageManager();

    public void start(int port) {

        Spark.port(port);
        staticFiles.location("/public"); // Static files;


//        post("/weboo/event", (request, sparkResponse) -> {
//            String widgetId = request.headers("weboo_widget_id");
//            String eventId = request.headers("weboo_handler_id");
//            String webooEventType = request.headers("weboo_event_type");
//
////            EventManager.EventType eventType = EventManager.EventType.valueOf(webooEventType);
//
////            AjaxResult ajaxResult = EventManager.handleRequest(widgetId, eventId, eventType);
//
//            sparkResponse.type("application/json");
//            return WebooUtil.toJson(ajaxResult);
//        });


        for (PageMeta pageMeta : pageManager.getAllPages()) {
            Spark.get(pageMeta.getPath(), (request, response) -> {
                try {
                    String pageId = UUID.randomUUID().toString();
                    Context.setRequest(request, response, pageId, pageMeta);

                    Page newPage = pageMeta.createNewPage();


                    Layout layout = newPage.getLayout();


                    String jsCommandCode = JsUtil.createJsCommandCodeDefinition(Context.getCurrentContext().getCommandDefinitions());
                    String jsEventsCode = JsUtil.createJsEventsCode(newPage.getWidgets());


                    layout.setLastBodyTag("""
                          <script>
                          // command definitions by widgets
                          %s
                          
                          // event call code
                          %s
                          </script>
                          """.formatted(jsCommandCode, jsEventsCode));

                  //  newPage.onRequest();

                    return layout.renderPage(newPage);

                } catch (Exception e) {
                    WebooUtil.printStackTraceError(e);

                    log.error("", e);
                    return "Exception " + e.getMessage();
                } finally {
                    Context.removeContext();
                }
            });
        }

        log.debug("http://localhost:" + port);
    }

    public static void addPage(Class<? extends Page> clazz) {
        pageManager.add(clazz);
    }


}
