package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.model.dto.User;
import jakarta.enterprise.inject.Default;
import jakarta.validation.Valid;

@Default
public class PingService {

    public String sayHello(@Valid User user) {
        return "Jambo! " + user.name;
    }

    public String ping() { return "Hello world!"; }
}
