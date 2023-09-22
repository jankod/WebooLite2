package hr.ja.weboo;

import hr.ja.weboo.js.JavaScriptFunction;
import lombok.Data;

import java.util.*;

public class PageRequestContext {

    private static final String REQUEST_ATTRIBUTE_NAME = "hr.ja.weboo.page_request";

    public void add(ClientServerEvent clientServerEvent) {
        getStore().add(clientServerEvent);
    }

    public void add(JavaScriptFunction function) {
        getStore().add(function);
    }


    private RequestStore getStore() {
        RequestStore store = Context.req().attribute(REQUEST_ATTRIBUTE_NAME);
        if(store == null) {
            setStore(new RequestStore());
        }
        return store;
    }

    private void setStore(RequestStore requestStore) {
        Context.req().attribute(REQUEST_ATTRIBUTE_NAME, requestStore);
    }


    public List<ClientServerEvent> getClientServerEvents() {
        return getStore().getEvents();
    }

    public void register(Class<? extends JavaScriptFunction> commandClass) {
        getStore().add(commandClass);
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
