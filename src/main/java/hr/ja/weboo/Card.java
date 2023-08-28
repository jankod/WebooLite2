package hr.ja.weboo;


import static hr.ja.weboo.WebooUtil.qute;
import static java.util.Map.of;

public class Card extends Widget {
    private final String title;

    public Card(String title) {
        this.title = title;
    }

    @Override
    public String toHtml() {
        //language=HTML
        String te = """
              <div class="card {classes}" id='{id}'>
                <div class='card-header'>
                    <h5 class="card-title">{title}</h5>
                </div>
                <div class="card-body">
                   {children.raw}
                </div>
              </div>
                            
              """;
        return qute(te, of(
              "classes", getClasses(),
              "id", getWidgetId(),
              "title", title,
              "children", toChildrenHtml()
        ));
    }
}
