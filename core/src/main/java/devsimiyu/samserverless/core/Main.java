package devsimiyu.samserverless.core;

import devsimiyu.samserverless.core.model.Address;
import devsimiyu.samserverless.core.model.User;
import devsimiyu.samserverless.core.security.Jwt;
import devsimiyu.samserverless.core.services.PingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

public class Main {

    public static void main(String[] args) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        containerInitializer.addExtensions(new Extension() {
            public void addJwt(@Observes AfterBeanDiscovery event) {
                System.out.println("AfterBeanDiscovery Extension CALLED!!");
                event.addBean().types(Jwt.class).scope(ApplicationScoped.class).addQualifier(Default.Literal.INSTANCE).createWith(obj -> new Jwt("jwt token string from main"
                ));
            }
        });
        try (SeContainer container = containerInitializer.initialize()) {
            PingService pingService = container.select(PingService.class).get();
            User user = new User();
            Address address = new Address();
            user.name = "";
            user.email = "mail";
            user.address = address;
            System.out.println(pingService.sayHello(user));
        }
    }
}