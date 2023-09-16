package hr.ja.weboo.js;

import hr.ja.weboo.WebooUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JavaScript("""
      $("#"+this.eleId).hide();
      """)
@Getter
@RequiredArgsConstructor
public class HideElement extends JavaScriptFunction {

    @JavaScriptParam
    private final String eleId;


    public static void main(String[] args) {
        HideElement command = new HideElement("22");
        System.out.println(WebooUtil.toJson(command));

        String code = JsUtil.createJsFunctionDefinitionCode(HideElement.class);
        System.out.println("---------------");
        System.out.println(code);
    }
}
