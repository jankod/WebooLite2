package hr.ja.weboo.db;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ObjectHolder<M extends HasId> {

    private Map<Long, M> store = new HashMap<>();

    public void save(M obj) {
        if (obj.getId() == null) {
            obj.setId(store.size() + 1L);
        }
        store.put(obj.getId(), obj);
    }

    public void delete(M obj) {
        store.remove(obj.getId());
    }

    public M findById(Long id) {
        return (M) store.get(id);
    }

    public int count() {
        return store.size();
    }

    public List<M> findAll() {
        return new ArrayList<>(store.values());
    }

}
