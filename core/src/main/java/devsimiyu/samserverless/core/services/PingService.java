package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.interceptors.Validate;
import devsimiyu.samserverless.core.model.dto.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.Dependent;

@Dependent
public class PingService {

    @Validate
    public String sayHello(User user) {
        return "Jambo! " + user.name;
    }

    @RolesAllowed("ADMIN")
    public String ping() { return "Hello world!"; }
}
