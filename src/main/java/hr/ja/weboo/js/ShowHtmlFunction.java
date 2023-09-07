package hr.ja.weboo.js;

import hr.ja.weboo.Widget;
import lombok.Getter;

@JavaScript("""
        $("#"+this.eleId).html(this.html);
      """)
@Getter
public class ShowHtmlFunction extends JavaScriptFunction {

    @JavaScriptParam
    private String html;

    @JavaScriptParam
    private final String eleId;

    public ShowHtmlFunction(Widget widget, String eleId) {
        html = widget.toHtml();
        this.eleId = eleId;
    }

    public ShowHtmlFunction(String html, String eleId) {
        this.html = html;
        this.eleId = eleId;
    }
}
