package hr.ja.weboo;

import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

@Getter
public class PageMeta {

    private final Class<? extends Page> pageClass;

    private String path;

    public PageMeta(Class<? extends Page> page) {
        this.pageClass = page;
        Path pathAnno = page.getAnnotation(Path.class);
        if (pathAnno != null)
            path = pathAnno.value();
    }

    @SneakyThrows
    public Page createNewPage() {
        Constructor<? extends Page> declaredConstructor = pageClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance();
    }

}
