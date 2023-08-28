package hr.ja.weboo;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class DefaultLayout implements Layout {

    @Setter
    private String lastBodyTag;

    private List<String> weboJavascriptFile = new ArrayList<>();

    public DefaultLayout() {
        weboJavascriptFile.add("jquery-3.7.0.min.js");
        weboJavascriptFile.add("weboo.js");
    }

    public String renderPage(Page page) {



        String body = WidgetUtil.pageToHtml(page);

        String template = """
              <!doctype html>
              <html lang="{lang}">
                {head.raw}
                <body class='container'>
                    <h1>default layout</h1>
                   {body.raw}
                   {lastBodyTag.raw}
                </body>
                </html>
              """;

        return WebooUtil.qute(template, Map.of(
              "head", head(page),
              "body", body,
              "lang", "en", // TODO: from config or???,
              "lastBodyTag", lastBodyTag
        ));
    }

    protected String head(Page page) {
        //language=HTML
        String template = """
                                      
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <link rel="icon" type="image/png" href="/weboo/favicon.png">
                  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
                  
                  <title>{title}</title>
                  <script>
                    const WEBOO_PAGE_ID = "{pageId}";
                  </script>
                   {#for file in weboJavascriptFile}
                        <script src='/weboo/{file}'></script>
                   {/for}    
                </head>
              """;

        return WebooUtil.qute(template,
              Map.
                    of(
                          "title", page.getTitle(),
                          "pageId", Context.getPageId(),
                          "weboJavascriptFile", weboJavascriptFile
                    ));
    }
}
