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
public class CustomJavaScript extends JavaScriptFunction {

    @JavaScriptParam
    private final String jsCode;

    /**
     * Key1, Value1, Key2, Value2, ...
     */
    @JavaScriptParam
    private String[] args;

    /**
     *
     * @param jsCode Js code
     * @param args key1, value1, key2... parameters user inside js code with this.key1.
     */
    public CustomJavaScript(String jsCode, String... args) {
        this.jsCode = jsCode;
        this.args = args;
    }

}
