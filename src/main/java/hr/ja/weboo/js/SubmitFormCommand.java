package hr.ja.weboo.js;

@JavaScript("""

    

      const headers = {
          Weboo_widget_id: widgetId,
          Weboo_event_name: 'submit',
          Weboo_page_id: WEBOO_PAGE_ID
      }
      $.ajax({
          method: 'POST',
          url: '/weboo/event',
          headers: headers,
          data: result
      }).done(function (ajaxResult, status) {
          weboo.handleAjaxResult(ajaxResult, status);
          }
      );
      """)
public class SubmitFormCommand extends JavaScriptFunction {
    private final String widgetId;

    public SubmitFormCommand(String widgetId) {
        this.widgetId = widgetId;
    }
}
