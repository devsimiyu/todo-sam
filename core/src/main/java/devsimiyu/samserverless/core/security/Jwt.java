package devsimiyu.samserverless.core.security;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class Jwt {

    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    public List<Role> getRoles() {
        System.out.println("Roles from JWT: " + token);
        return List.of(Role.ADMIN);
    }
}
