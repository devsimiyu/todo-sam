package devsimiyu.samserverless.core.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import devsimiyu.samserverless.core.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

@Configuration
public class Mongo {

    @Value("${spring.data.mongodb.url:#{environment.MONGO_URI}}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database:#{environment.MONGO_DB}}")
    private String mongoDatabase;

    @Bean(destroyMethod = "close")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoClient createMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoRepositoryFactory createMongoRepositoryFactory(MongoClient mongoClient) {
        MongoOperations mongoOperations = new MongoTemplate(mongoClient, mongoDatabase);
        return new MongoRepositoryFactory(mongoOperations);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TodoRepository createTodoRepository(MongoRepositoryFactory mongoRepositoryFactory) {
        return mongoRepositoryFactory.getRepository(TodoRepository.class);
    }
}
