package hr.ja.weboo.components.bs;

public enum Color {
    PRIMARY,
    SECONDARY,
    SUCCESS,
    DANGER,
    WARNING,
    INFO,
    LIGHT,
    DARK;

    public String toCssClass() {
        return "btn-" + this.name().toLowerCase();
    }

    public String toName() {
        return this.name().toLowerCase();
    }
}
