package hr.ja.demo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class User {

    Long id;
    String name;
}
