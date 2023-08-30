package hr.ja.weboo;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
class PageManager {

    private final List<PageMeta> pages = new ArrayList<>();

    public List<PageMeta> getAllPages() {
        return pages;
    }

    public boolean contains(Class<? extends Page> page) {
        Optional<PageMeta> first = pages.stream().filter(p -> p.getPageClass().equals(page)).findFirst();
        return first.isPresent();
    }

    public void add(Class<? extends Page> page) {
        PageMeta pageHolder = new PageMeta(page);
        pages.add(pageHolder);

    }

    public String getPath(Class<? extends Page> page) {
        return find(page).orElseThrow().getPath();
    }

    private Optional<PageMeta> find(Class<? extends Page> page) {
        return pages.stream().filter(pageHolder1 -> pageHolder1.getPageClass().equals(page)).findFirst();

    }
}
