package devsimiyu.samserverless.core;

import devsimiyu.samserverless.core.config.IoC;
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
        String token = "jwt primitive token string from main";
        try (SeContainer container = IoC.INSTANCE.initialize(token)) {
            PingService pingService = container.select(PingService.class).get();
            System.out.println("Ping Message: " + pingService.ping());
        }
    }
}