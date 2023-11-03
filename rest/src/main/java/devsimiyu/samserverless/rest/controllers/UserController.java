package devsimiyu.samserverless.rest.controllers;

import devsimiyu.samserverless.core.model.entity.User;
import devsimiyu.samserverless.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Long create() {
        User user = userService.createUser();
        return user.id;
    }
}
