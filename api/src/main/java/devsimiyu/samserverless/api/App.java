package devsimiyu.samserverless.api;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import devsimiyu.samserverless.core.services.PingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String token = "jwt primitive token string from main";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        ApplicationContext serviceLocator = new AnnotationConfigApplicationContext("devsimiyu.samserverless");
        PingService pingService = serviceLocator.getBean(PingService.class);
        String message = pingService.ping();

        String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", message);

        return response.withStatusCode(200).withBody(output);
    }
}
