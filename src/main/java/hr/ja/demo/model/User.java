package hr.ja.demo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import

      lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@FieldNameConstants
@NoArgsConstructor
public class User {

    private static long maxId = 1L;

    private final static Map<Long, User> db = new HashMap<>();

    private Long id;

    @NotEmpty
    private String name;

    @NotNull(message = "Please insert role!")
    private Role role;

    public User(String name) {
        this.name = name;
    }

    public static User findById(Long id) {
        return db.get(id);
    }

    public synchronized void save() {
        if (id == null) {
            id = maxId++;
        }
        maxId = Math.max(maxId, id);
        db.put(id, this);
    }

    public static List<User> getAll() {
        return new ArrayList<>(db.values());
    }

}
