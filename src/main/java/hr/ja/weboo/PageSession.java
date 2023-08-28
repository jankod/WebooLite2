package hr.ja.weboo;

public class PageSession {


    private final PageMeta pageMeta;
    private final String pageId;
    private final Page newPage;

    public PageSession(PageMeta pageMeta, String pageId, Page newPage) {

        this.pageMeta = pageMeta;
        this.pageId = pageId;
        this.newPage = newPage;
    }
}
