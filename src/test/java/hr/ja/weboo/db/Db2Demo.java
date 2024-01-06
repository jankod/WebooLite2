package hr.ja.weboo.db;

import lombok.Data;

import java.util.List;

public class Db2Demo {

    public static void main(String[] args) {

    }
}

@Data
class Model implements HasId {
    Long id;

    static ObjectHolder<HasId> store = new ObjectHolder<>();


    void save() {
        store.save(this);
    }

    static List<HasId> findAll() {
        return store.findAll();
    }
}
