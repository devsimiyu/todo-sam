package devsimiyu.samserverless.core.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class Principal {

    @Inject
    private Jwt token;

    public String getName() {
        return "";
    }

    public List<String> getRoles() { return List.of(Role.DEV.name()); }

}
