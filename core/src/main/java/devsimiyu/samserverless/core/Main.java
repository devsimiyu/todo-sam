package devsimiyu.samserverless.core;

import devsimiyu.samserverless.core.model.dto.Address;
import devsimiyu.samserverless.core.model.dto.TodoItem;
import devsimiyu.samserverless.core.model.entity.Todo;
import devsimiyu.samserverless.core.model.entity.User;
import devsimiyu.samserverless.core.security.Jwt;
import devsimiyu.samserverless.core.services.PingService;
import devsimiyu.samserverless.core.services.TodoService;
import devsimiyu.samserverless.core.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

public class Main {

    public static void main(String[] args) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance().addExtensions(new Extension() {
            public void addJwt(@Observes AfterBeanDiscovery event) {
                System.out.println("AfterBeanDiscovery Extension CALLED!!");
                Jwt token = () -> "jwt primitive token string from main";
                event.addBean().types(Jwt.class).scope(ApplicationScoped.class).addQualifiers(Default.Literal.INSTANCE).createWith(context -> token);
            }
        });
        try (SeContainer container = containerInitializer.initialize()) {
            TodoService todoService = container.select(TodoService.class).get();
            Todo todo = todoService.createTodo();
            System.out.println("Created Todo: " + todo);
            TodoItem todoItem = todoService.getTodo(todo.id);
            System.out.println("Get TodoItem: " + todoItem.getId());
            UserService userService = container.select(UserService.class).get();
            User user = userService.createUser();
            System.out.println("Created User: " + user);
        }
    }
}