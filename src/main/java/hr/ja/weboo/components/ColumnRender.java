package hr.ja.weboo.components;

@FunctionalInterface
public interface ColumnRender<M, R> {
    R render(M model);
}
