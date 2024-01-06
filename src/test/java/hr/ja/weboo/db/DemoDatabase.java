package hr.ja.weboo.db;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DemoDatabase {

    public static void main(String[] args) {

        Project p = new Project();
        p.setName("My project");

        p.persist();

        ArrayList<Project> all = Project.findAll();
        System.out.println(all);

    }
}
@Data
@FieldNameConstants
class Project extends DbModel {
    String name;
}


abstract class DbModel {
    private static Map<Long, DbModel> store = new HashMap<>();

    public Long id;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + id + ">";
    }

    public void persist() {
        if (id == null) {
            id = store.size() + 1L;
        }
        store.put(id, this);
    }

    public static <T extends DbModel> T findById(Long id) {
        return (T) store.get(id);
    }

    public static <T extends DbModel> ArrayList<T> findAll( ){
        return new ArrayList(store.values());
    }
}

