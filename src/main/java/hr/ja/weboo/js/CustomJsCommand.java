package hr.ja.weboo.js;

import lombok.Getter;

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
public class CustomJsCommand extends JsCommand {

    @JavaScriptParam
    private final String jsCode;

    @JavaScriptParam
    private String[] args;

    /**
     *
     * @param jsCode Js code
     * @param args key, value parameters user inside js code
     */
    public CustomJsCommand(String jsCode, String... args) {
        this.jsCode = jsCode;
        this.args = args;
    }

}
