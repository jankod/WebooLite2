package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class PageSessionManager {

    /**
     * pageId => Holder
     */
    private final HashMap<String, Holder> store = new HashMap<>();

    public void add(ClientServerEvent clientServerEvent, String pageId) {
        getHolder(pageId).add(clientServerEvent);
    }

    public void add(JavaScriptFunction function, String pageId) {
        getHolder(pageId).add(function);
    }

    private Holder getHolder(String pageId) {
        Holder holder;
        if (!store.containsKey(pageId)) {
            holder = new Holder();
            store.put(pageId, holder);
        } else {
            holder = store.get(pageId);
        }
        return holder;
    }


    public List<ClientServerEvent> getEvents(String pageId) {
        Holder holder = store.get(pageId);
        if (holder == null) {
            return Collections.emptyList();
        }
        return holder.getEvents();
    }

    public void register(Class<? extends JavaScriptFunction> commandClass, String pageId) {
        Holder holder = store.get(pageId);
        holder.add(commandClass);
    }
}

@Data
class Holder {
    private final List<ClientServerEvent> events = new ArrayList<>();

    private final List<JavaScriptFunction> functions = new ArrayList<>();

    private final Set<Class<? extends JavaScriptFunction>> functionDefinitions = new HashSet<>();


    public void add(ClientServerEvent clientServerEvent) {
        events.add(clientServerEvent);
    }

    public void add(JavaScriptFunction function) {
        this.functions.add(function);
    }

    public void add(Class<? extends JavaScriptFunction> funcClass) {
        functionDefinitions.add(funcClass);
    }
}
