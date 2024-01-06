package hr.ja.weboo;


import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class DefaultLayout implements Layout {

    @Setter
    private String lastBodyHtmlChunk;

    private List<String> javascriptFiles = new ArrayList<>();
    private List<String> cssFiles = new ArrayList<>();

    public DefaultLayout() {
        javascriptFiles.add("jquery-3.7.0.min.js");
        javascriptFiles.add("weboo.js");
    }

    public String makeTemplate(Page page) {


        String pageHtml = WidgetUtil.pageToHtml(page);

        String template = """
              <!doctype html>
              <html lang="{lang}">
                {head.raw}
                <body class='container'>
                    <h1>Default layout</h1>
                   {pageHtml.raw}
                   {lastBodyTag.raw}
                </body>
                </html>
              """;

        return WebooUtil.qute(template, Map.of(
              "head", head(page),
              "pageHtml", pageHtml,
              "lang", "en", // TODO: from config or???,
              "lastBodyTag", lastBodyHtmlChunk
        ));
    }

    @Override
    public Collection<Class<? extends JavaScriptFunction>> getJavaScript() {
        return null;
    }

    protected String head(Page page) {
        //language=HTML
        String template = """
                                      
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <link rel="icon" type="image/png" href="/weboo/favicon.png">
                  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
                  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
                  
                  <title>{title}</title>
                  <script>
                    const WEBOO_PAGE_ID = "{pageId}";
                  </script>
                   {#for file in webooJavascriptFile}
                        <script src='/weboo/{file}'></script>
                   {/for}    
                </head>
              """;

        return WebooUtil.qute(template,
              Map.
                    of(
                          "title", page.getTitle(),
                          "pageId", Context.getPageId(),
                          "webooJavascriptFile", javascriptFiles
                    ));
    }
}
