package hr.ja.weboo.js;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@JavaScript("""
      console.log("Exe js custom code: ", this)
      const func = new Function(this.jsCode);
            
      const result = this.args.reduce((obj, value, index, array) => {
          if (index % 2 === 0) {
              obj[value] = array[index + 1];
          }
          return obj;
      }, {});
            
      return func.apply(result);
            
      """)
@Getter
public class CustomCodeCommand extends JsCommand {

    @JavaScriptParam
    private final String jsCode;

    @JavaScriptParam
    private String[] args;

    public CustomCodeCommand(String jsCode, String... args) {
        this.jsCode = jsCode;
        this.args = args;
    }

}
