package hr.ja.weboo;

public interface Layout {

    void setLastBodyHtmlChunk(String html);

    String renderPage(Page page);




}
