package hr.ja.weboo;

import io.quarkus.qute.Expression;
import io.quarkus.qute.ResultMapper;
import io.quarkus.qute.TemplateNode;

public class QuteWidgetResultMapper implements ResultMapper {


    @Override
    public boolean appliesTo(TemplateNode.Origin origin, Object result) {
        return result instanceof Widget;
    }

    @Override
    public String map(Object result, Expression expression) {
        Widget w = (Widget) result;
        return w.toHtml();
    }
}
