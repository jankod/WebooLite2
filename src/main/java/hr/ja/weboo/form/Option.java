package hr.ja.weboo.form;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Option {
    private String key;
    private String label;
    private boolean selected;

    private boolean disable = false;

    public Option(String key, String label, boolean selected) {
        this.key = key;
        this.label = label;
        this.selected = selected;
    }
}
