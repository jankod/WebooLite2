package hr.ja.weboo;

@FunctionalInterface
public interface PageFilter {

    boolean allow(Class<? extends Page> page);
}
