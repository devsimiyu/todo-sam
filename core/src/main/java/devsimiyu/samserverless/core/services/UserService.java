package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.model.entity.User;
import devsimiyu.samserverless.core.repository.UserRepository;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.springframework.transaction.annotation.Transactional;

@Default
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User createUser() {
        User user = new User();
        user.email = "taelor@gray.com";
        user.username = "gray";
        userRepository.save(user);
        return user;
    }
}
