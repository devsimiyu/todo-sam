package devsimiyu.samserverless.core.config;

import devsimiyu.samserverless.core.interceptors.Auth;
import devsimiyu.samserverless.core.security.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;

@FunctionalInterface
public interface IoC {

    SeContainer initialize(String token);

    static IoC INSTANCE = token -> {
        Extension authSession = new Extension() {
            public void addJwt(@Observes AfterBeanDiscovery event) {
                System.out.println("AfterBeanDiscovery Extension CALLED!!");
                Jwt jwt = () -> token;
                event.addBean().types(Jwt.class).scope(ApplicationScoped.class).addQualifiers(Default.Literal.INSTANCE).createWith(context -> jwt);
            }
        };
        Extension authInterceptor = new Extension() {
            public void addJwt(@Observes ProcessAnnotatedType<?> event) {
                System.out.println("ProcessAnnotatedType Extension CALLED!!");
                event.configureAnnotatedType().filterMethods(annotatedMethod -> annotatedMethod.isAnnotationPresent(RolesAllowed.class)).forEach(annotatedMethodConfigurator -> annotatedMethodConfigurator.add(Auth.Literal.INSTANCE));
            }
        };
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance().addExtensions(authSession, authInterceptor);
        return containerInitializer.initialize();
    };
}
