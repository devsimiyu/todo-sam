package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.model.entity.User;
import devsimiyu.samserverless.core.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Dependent
public class UserService {

    @Inject
    UserRepository userRepository;

    @RolesAllowed("ADMIN")
    @Transactional
    public User createUser() {
        User user = new User();
        user.email = "rodgers@wanyonyi.com";
        user.username = "rodgerswanyonyi";
        userRepository.save(user);
        return user;
    }
}
