package hr.ja.weboo.components;

import hr.ja.weboo.WebooUtil;
import hr.ja.weboo.Widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SimpleTable<M> extends Widget {

    private Collection<M> data;

    private List<Column<M>> columns = new ArrayList<>();

    public SimpleTable(Collection<M> data) {
        this.data = data;
        addClass("table");
    }

    public Column<M> column(String name, ColumnRender<M, Object> columnValue) {
        Column<M> column = new Column<>(name, columnValue);
        columns.add(column);
        return column;
    }

    @Override
    public String toHtml() {


        String template = """
              <table {attr.raw}>
                 <thead>
                     <tr>
                      {#for c in columns}
                        <th>{c.name}</th>
                      {/for}
                     </tr>
                     <tbody>
                        {#for d in data}
                         <tr>
                         {#for c in columns}
                           <td>{c.columnValue.render(d)}</td>
                           {/for}
                         </tr>
                        {/for}
                        
                   </thead>
              </table>
              """;

        return WebooUtil.qute(template, Map.of(
              "columns", columns,
              "data", data,
              "attr", getIdClassStyleAttr()
        ));
    }
}
