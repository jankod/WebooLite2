package hr.ja.weboo;

import hr.ja.weboo.js.AjaxResult;
import hr.ja.weboo.js.JsUtil;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import spark.Spark;

import static spark.Spark.post;
import static spark.Spark.staticFiles;

@Slf4j
@UtilityClass
@Getter
public class Weboo {

    private PageManager pageManager = new PageManager();

    public void start(int port) {

        Spark.port(port);
        staticFiles.location("/public"); // Static files;

        post("/weboo/event", (request, response) -> {

            String widgetId = request.headers("Weboo_widget_id");
            String handlerId = request.headers("Weboo_handler_id");
            String eventName = request.headers("Weboo_event_name");
            String pageId = request.headers("Weboo_page_id");

            Context.setCurrentContext(request, response, pageId, null);
            response.type("application/json");


            ServerHandler eventHandler = hr.ja.weboo.js.ServerHandler.get(handlerId, widgetId, pageId);
            if (eventHandler == null) {
                log.error("Cannot find event handler!!!");
                return WebooUtil.toJson(AjaxResult.alert("Error, cannot find event handler!"));
            }
            return WebooUtil.toJson(eventHandler.handle());

        });


        for (PageMeta pageMeta : pageManager.getAllPages()) {
            Spark.get(pageMeta.getPath(), (request, response) -> {
                try {

                    String pageId = WebooUtil.createPageId();

                    Context.setCurrentContext(request, response, pageId, pageMeta);
                    Page newPage = pageMeta.createNewPage();

                    Layout layout = newPage.getLayout();
                    if (layout == null) {
                        layout = new DefaultLayout();
                    }

                    String jsCommandCode = JsUtil.createJsCommandCodeDefinition(Context.getCurrentContext().getCommandDefinitions());
                    String jsEventsCode = JsUtil.createJsEventsCode(newPage.getWidgets());

                    layout.setLastBodyHtmlChunk("""
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


    public static String getPath(Class<? extends Page> page) {
        if (!pageManager.contains(page)) {
            //pageManager.add(page);
            throw new RuntimeException("Not add page " + page);
        }
        return pageManager.getPath(page);
    }

}
