package hr.ja.weboo.db;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class MemoryObject<M> {

    private Map<Long, M> store = new HashMap<>();

    private Long id;

    public void save() {

    }

}
