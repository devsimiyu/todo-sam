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

    public List<Role> getRoles() {
        System.out.println("Roles from JWT: " + token.get());
        return List.of(Role.ADMIN);
    }

}
