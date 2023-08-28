package hr.ja.weboo.form;

public class Radiobuttons extends FormFieldWidget {


    @Override
    public String toHtml() {
        return """
                     {#for item in options}
              <div class="form-check" {autofocus}>
                <input class="form-check-input" type="radio" name="{item.key}" s>
                <label class="form-check-label" for="{name}">
                  {item.label}
                </label>
              </div>
              {/for}
                            
              """;
    }

    @Override
    public void bindTo(Object modelObject) {
        throw new RuntimeException("not implemented yet");
    }
}
