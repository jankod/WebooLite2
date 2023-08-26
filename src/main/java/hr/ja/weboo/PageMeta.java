package hr.ja.weboo;

import lombok.Getter;
import lombok.SneakyThrows;

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
        return pageClass.getConstructor().newInstance();
    }


    public boolean isCurrent() {
        Class<? extends Page> currentPage = Context.getCurrentPage();
        if(currentPage != null) {
            return currentPage.equals(pageClass);
        }
        return false;
    }
}
