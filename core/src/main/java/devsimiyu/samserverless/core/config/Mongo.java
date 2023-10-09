package devsimiyu.samserverless.core.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import devsimiyu.samserverless.core.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

@Dependent
public class Mongo {

    @Produces
    @ApplicationScoped
    public MongoClient createMongoClient() {
        String mongoUri = System.getenv("MONGO_URI");
        MongoClient mongoClient = MongoClients.create(mongoUri);
        System.out.println("Created MongoClient: " + mongoClient);
        return mongoClient;
    }

    @Produces
    public MongoRepositoryFactory createMongoRepositoryFactory(MongoClient mongoClient) {
        MongoOperations mongoOperations = new MongoTemplate(mongoClient, System.getenv("MONGO_DB"));
        return new MongoRepositoryFactory(mongoOperations);
    }

    @Produces
    public TodoRepository createTodoRepository(MongoRepositoryFactory mongoRepositoryFactory) {
        return mongoRepositoryFactory.getRepository(TodoRepository.class);
    }

    public void destroyMongoClient(@Disposes MongoClient mongoClient) {
        System.out.println("Disposing MongoClient: " + mongoClient);
        mongoClient.close();
    }
}
