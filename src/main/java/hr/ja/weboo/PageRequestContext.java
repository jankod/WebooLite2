package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class PageRequestContext {

    /**
     * pageId => Holder
     */
    private final HashMap<String, JavascriptHolder> store = new HashMap<>();

    public void add(ClientServerEvent clientServerEvent, String pageId) {
        getHolder(pageId).add(clientServerEvent);
    }

    public void add(JavaScriptFunction function, String pageId) {
        getHolder(pageId).add(function);
    }

    private JavascriptHolder getHolder(String pageId) {
        JavascriptHolder holder;
        if (!store.containsKey(pageId)) {
            holder = new JavascriptHolder();
            store.put(pageId, holder);
        } else {
            holder = store.get(pageId);
        }
        return holder;
    }


    public List<ClientServerEvent> getClientServerEvents(String pageId) {
        JavascriptHolder holder = store.get(pageId);
        if (holder == null) {
            return Collections.emptyList();
        }
        return holder.getEvents();
    }

    public void register(Class<? extends JavaScriptFunction> commandClass, String pageId) {
        JavascriptHolder holder = store.get(pageId);
        holder.add(commandClass);
    }

    public static void setTitle(String title) {
    }
}

@Data
class JavascriptHolder {
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
