package devsimiyu.samserverless.core.config;

import devsimiyu.samserverless.core.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.Map;

@Configuration
public class Postgres {

    @Value("${spring.datasource.url:#{environment.DB_JDBC}}")
    private String pgUrl;

    @Value("${spring.datasource.username:#{environment.DB_USER}}")
    private String pgUser;

    @Value("${spring.datasource.password:#{environment.DB_PASSWORD}}")
    private String pgPassword;

    @Value("${spring.datasource.driver-class-name:#{environment.DB_DRIVER}}")
    private String pgDriver;

    @Bean(destroyMethod = "close")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> props = Map.of(
                "javax.persistence.jdbc.driver", pgDriver,
                "javax.persistence.jdbc.url", pgUrl,
                "javax.persistence.jdbc.user", pgUser,
                "javax.persistence.jdbc.password", pgPassword,
                "hibernate.show_sql", "true",
                "hibernate.transaction.jta.platform", "devsimiyu.samserverless.core.config.Transaction"
        );
        return Persistence.createEntityManagerFactory("todo-sam-postgres", props);
    }

    @Bean(destroyMethod = "close")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager createPlatformTransactionManager(UserTransaction userTransaction, TransactionManager transactionManager) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(transactionManager);
        jtaTransactionManager.setUserTransaction(userTransaction);
        return jtaTransactionManager;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JpaRepositoryFactory createJpaRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserRepository createUserRepository(JpaRepositoryFactory jpaRepositoryFactory) {
        return jpaRepositoryFactory.getRepository(UserRepository.class);
    }
}
