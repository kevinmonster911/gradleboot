package hello.Domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by kevinmonster on 16/10/12.
 */
@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
