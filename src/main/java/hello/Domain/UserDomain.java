package hello.Domain;

import hello.dao.UserDao;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class UserDomain {

    @Autowired
    private UserDao userDao;

    private User primaryEntity;

    public User save(User user) {
        userDao.insert(user);
        primaryEntity = user;
        return user;
    }

}
