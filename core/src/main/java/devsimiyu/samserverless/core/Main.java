package devsimiyu.samserverless.core;

import devsimiyu.samserverless.core.interceptors.Auth;
import devsimiyu.samserverless.core.model.dto.Address;
import devsimiyu.samserverless.core.model.dto.TodoItem;
import devsimiyu.samserverless.core.model.dto.User;
import devsimiyu.samserverless.core.model.entity.Todo;
import devsimiyu.samserverless.core.security.Jwt;
import devsimiyu.samserverless.core.services.PingService;
import devsimiyu.samserverless.core.services.TodoService;
import devsimiyu.samserverless.core.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.*;

public class Main {

    public static void main(String[] args) {
        Extension authSession = new Extension() {
            public void addJwt(@Observes AfterBeanDiscovery event) {
                System.out.println("AfterBeanDiscovery Extension CALLED!!");
                Jwt token = () -> "jwt primitive token string from main";
                event.addBean().types(Jwt.class).scope(ApplicationScoped.class).addQualifiers(Default.Literal.INSTANCE).createWith(context -> token);
            }
        };
        Extension authInterceptor = new Extension() {
            public void addJwt(@Observes ProcessAnnotatedType<?> event) {
                System.out.println("ProcessAnnotatedType Extension CALLED!!");
                event.configureAnnotatedType().filterMethods(annotatedMethod -> annotatedMethod.isAnnotationPresent(RolesAllowed.class)).forEach(annotatedMethodConfigurator -> annotatedMethodConfigurator.add(Auth.Literal.INSTANCE));
            }
        };
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance().addExtensions(authSession, authInterceptor);
        try (SeContainer container = containerInitializer.initialize()) {
            PingService pingService = container.select(PingService.class).get();
            System.out.println("Ping Message: " + pingService.ping());
        }
    }
}