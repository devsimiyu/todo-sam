package devsimiyu.samserverless.api;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import devsimiyu.samserverless.core.model.dto.Address;
import devsimiyu.samserverless.core.model.dto.User;
import devsimiyu.samserverless.core.security.Jwt;
import devsimiyu.samserverless.core.services.PingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.Extension;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        containerInitializer.addExtensions(new Extension() {
            public void addJwt(@Observes AfterBeanDiscovery event) {
                System.out.println("AfterBeanDiscovery Extension CALLED!!");
                Jwt token = () -> "jwt primitive token string from main";
                event.addBean().types(Jwt.class).scope(ApplicationScoped.class).addQualifier(Default.Literal.INSTANCE).createWith(obj -> token);
            }
        });

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        try (SeContainer container = containerInitializer.initialize()) {
            PingService pingService = container.select(PingService.class).get();
            User user = new User();
            Address address = new Address();
            user.name = "John Doe";
            user.email = "mail@example.org";
            user.address = address;
            address.apartment = "Naivas";
            address.street = "Gakere";
            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pingService.sayHello(user));

            return response
                    .withStatusCode(200)
                    .withBody(output);

        }
        catch (Exception e) {
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }
    }
}
