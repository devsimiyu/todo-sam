package devsimiyu.samserverless.core;

import devsimiyu.samserverless.core.services.PingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext serviceLocator = new AnnotationConfigApplicationContext("devsimiyu.samserverless");
        PingService pingService = serviceLocator.getBean(PingService.class);
        String message = pingService.ping();
        System.out.println("MESSAGE FROM PingService: " + message);
    }
}
