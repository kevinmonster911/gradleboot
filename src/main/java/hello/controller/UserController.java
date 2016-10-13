package hello.controller;

import hello.Domain.User;
import hello.Domain.UserDomain;
import hello.action.user.UserCreationAction;
import hello.dao.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevinmonster on 16/10/13.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserDomain userDomain;

    @PostMapping("/users")
    public ApiResult saveUsers(@RequestBody UserCreationAction action) {
        User user = new User();
        BeanUtils.copyProperties(action, user);
        User result = userDomain.save(user);
        return result(result);
    }


}
