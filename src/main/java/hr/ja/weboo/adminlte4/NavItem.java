package hr.ja.weboo.adminlte4;

import hr.ja.weboo.Widget;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class NavItem extends Widget {


    private String iconClass;
    private String url;
    private Widget label;

    boolean open = false;

    public NavItem(String iconClass, String url, Widget label) {
        this.iconClass = iconClass;
        this.url = url;
        this.label = label;
    }


    @Override
    public String toHtml() {
        return qute("""
                    <li class="nav-item {open}">
                    <a href="{url}" class="nav-link">
                        <i class="nav-icon bi {iconClass} "></i>
                        <p>{label}
                        
                        {#if children.size != 0}    
                        <i class="nav-arrow bi bi-chevron-right"></i>
                        {/if}
                        </p>
                       
                    </a>
                        {#if children.size != 0}
                        <ul class="nav nav-treeview">
                          {#for c in children}
                             {c}
                          {/for}
                        </ul>
                        {/if} 
                    <li>
                    
                    """,
              "url", url,
              "iconClass", iconClass,
              "label", label,
              "open", open ? "menu-open" : "",
              "children", getChildren()
        );
    }
}
