package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.interceptors.Auth;
import devsimiyu.samserverless.core.interceptors.Validate;
import devsimiyu.samserverless.core.model.dto.User;
import devsimiyu.samserverless.core.security.Role;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class PingService {

    @Auth(Role.ADMIN)
    @Validate
    public String sayHello(User user) {
        return "Jambo! " + user.name;
    }
}
