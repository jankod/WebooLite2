package hr.ja.weboo;

import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@UtilityClass
public class ClientServerManager {

    /**
     * pageId
     */
    private HashMap<String, Holder> store = new HashMap<>();

    public void add(ClientServerEvent clientServerEvent, String pageId) {
        Holder holder;
        if(!store.containsKey(pageId)) {
            holder = new Holder();
            store.put(pageId, holder);
        }else {
            holder = store.get(pageId);
        }
        holder.add(clientServerEvent);
    }


}

@Data
class Holder {
    List<ClientServerEvent> events = new ArrayList<>();

    public void add(ClientServerEvent clientServerEvent) {
        events.add(clientServerEvent);
    }
}
