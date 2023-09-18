package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Data;

import java.util.*;

public class PageRequestContext {

    /**
     * pageId => Holder
     */
    //private final HashMap<String, JavascriptHolder> store = new HashMap<>();

//    private final ThreadLocal<RequestStore> store = new InheritableThreadLocal<>();

    private static final String REQUEST_ATTRIBUTE_NAME = "hr.ja.weboo.page_request";

    public void add(ClientServerEvent clientServerEvent, String pageId) {
        getHolder(pageId).add(clientServerEvent);
    }

    public void add(JavaScriptFunction function, String pageId) {
        getHolder(pageId).add(function);
    }

    private RequestStore getHolder(String pageId) {
        RequestStore store = Context.req().attribute(REQUEST_ATTRIBUTE_NAME);
        if(store == null) {
            store = new RequestStore();
        }

        RequestStore holder;
        if (!store.get().getPageId().equals(pageId)) {
            holder = new RequestStore();

            store.put(pageId, holder);
        } else {
            holder = store.get(pageId);
        }
        return holder;
    }


    public List<ClientServerEvent> getClientServerEvents(String pageId) {
        RequestStore holder = store.get(pageId);
        if (holder == null) {
            return Collections.emptyList();
        }
        return holder.getEvents();
    }

    public void register(Class<? extends JavaScriptFunction> commandClass, String pageId) {
        RequestStore holder = store.get(pageId);
        holder.add(commandClass);
    }

}

@Data
class RequestStore {

    private final List<ClientServerEvent> events = new ArrayList<>();

    private final List<JavaScriptFunction> clientFunction = new ArrayList<>();

    private final Set<Class<? extends JavaScriptFunction>> functionDefinitions = new HashSet<>();


    public void add(ClientServerEvent clientServerEvent) {
        events.add(clientServerEvent);
    }

    public void add(JavaScriptFunction function) {
        this.clientFunction.add(function);
    }

    public void add(Class<? extends JavaScriptFunction> funcClass) {
        functionDefinitions.add(funcClass);
    }
}
