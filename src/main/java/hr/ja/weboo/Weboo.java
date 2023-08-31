package hr.ja.weboo;

import hr.ja.weboo.js.AjaxResult;
import hr.ja.weboo.js.JavaScriptFunction;
import hr.ja.weboo.js.JsUtil;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import spark.Spark;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static spark.Spark.post;
import static spark.Spark.staticFiles;

@Slf4j
@Getter
@UtilityClass
public class Weboo {

    private boolean development = true;

    public void start(int port) {

        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        Class<? extends StackTraceElement> mainClass = stackTrace[1].getClass();

        scanForPages(mainClass);

        scanForFunctions(mainClass);

        Spark.port(port);
        staticFiles.location("/public"); // Static files;

        post("/weboo/event", (request, response) -> {

            String widgetId = request.headers("Weboo_widget_id");
            String handlerId = request.headers("Weboo_handler_id");
            String eventName = request.headers("Weboo_event_name");
            String pageId = request.headers("Weboo_page_id");

            Context.setCurrentContext(request, response, pageId, null);
            response.type("application/json");


            ServerHandler eventHandler = ServerHandlerManager.get(handlerId, widgetId, pageId);
            if (eventHandler == null) {
                log.error("Cannot find event handler!!!");
                return WebooUtil.toJson(new AjaxResult().alert("Error, cannot find event handler, try reload page!"));
            }
            return WebooUtil.toJson(eventHandler.handle());

        });

        for (PageMeta pageMeta : PageManager.getAllPages()) {
            Spark.get(pageMeta.getPath(), (request, response) -> {
                try {
                    String pageId = WebooUtil.createPageId();

                    Context.setCurrentContext(request, response, pageId, pageMeta);
                    Page newPage = pageMeta.createNewPage();

                    Layout layout = newPage.getLayout();
                    if (layout == null) {
                        layout = new DefaultLayout();
                    }

                    String jsCommandCode = JsUtil.createJsFunctionCodeDefinition(JavaScriptManager.getFunctions());

                    String jsEventsCode = JsUtil.createJsEventsCode(PageSessionManager.getEvents(Context.getPageId()));

                    layout.setLastBodyHtmlChunk("""
                          <script>
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

    private void scanForFunctions(Class<? extends StackTraceElement> mainClass) {
        String packageName = mainClass.getPackage().getName();
        Collection<URL> urls = new ArrayList<>(ClasspathHelper.forPackage(packageName));
        urls.add(ClasspathHelper.forClass(Weboo.class));
        Reflections reflections = new Reflections(new ConfigurationBuilder()
              .setUrls(urls));
        //.filterInputsBy(new FilterBuilder()
        //    .includePackage(packageName)));

        Set<Class<? extends JavaScriptFunction>> jsFounded = reflections.getSubTypesOf(JavaScriptFunction.class);
        for (Class<? extends JavaScriptFunction> p : jsFounded) {
            //    log.debug("Found function: {}", p);
            addJavascript(p);
        }
    }

    private void addJavascript(Class<? extends JavaScriptFunction> functionClass) {
        JavaScriptManager.add(functionClass);
    }

    private void scanForPages(Class<? extends StackTraceElement> mainClass) {
        String packageName = mainClass.getPackage().getName();
        Collection<URL> urls = new ArrayList<>(ClasspathHelper.forPackage(packageName));
        urls.add(ClasspathHelper.forClass(Weboo.class));

        Reflections reflections = new Reflections(new ConfigurationBuilder()
              .setUrls(urls));


        Set<Class<? extends Page>> pagesFounded = reflections.getSubTypesOf(Page.class);
        for (Class<? extends Page> p : pagesFounded) {
            //    log.debug("Found page: {}", p);
            PageManager.add(p);
        }
    }

    public void addPage(Class<? extends Page> clazz) {
        if (clazz.getAnnotation(Path.class) == null) {
            throw new RuntimeException("Page does not have  @Path annotation.");
        }
        PageManager.add(clazz);
    }


    public String getPath(Class<? extends Page> page) {
        if (!PageManager.contains(page)) {
            //pageManager.add(page);
            throw new RuntimeException("Not add page " + page);
        }
        return PageManager.getPath(page);
    }

}
