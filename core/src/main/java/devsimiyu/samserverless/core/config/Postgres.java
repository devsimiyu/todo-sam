package devsimiyu.samserverless.core.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import devsimiyu.samserverless.core.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.UserTransaction;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import java.util.Map;

@Dependent
public class Postgres {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> props = Map.of(
                "javax.persistence.jdbc.driver", "org.postgresql.Driver",
                "javax.persistence.jdbc.url", System.getenv("POSTGRES_JDBC"),
                "javax.persistence.jdbc.user", System.getenv("POSTGRES_USER"),
                "javax.persistence.jdbc.password", System.getenv("POSTGRES_PASSWORD"),
                "hibernate.show_sql", "true",
                "hibernate.transaction.jta.platform", "devsimiyu.samserverless.core.config.Transaction"
        );
        return Persistence.createEntityManagerFactory("todo-sam-postgres", props);
    }

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Produces
    public JpaRepositoryFactory createJpaRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager);
    }

    @Produces
    public UserRepository createUserRepository(JpaRepositoryFactory jpaRepositoryFactory) {
        return jpaRepositoryFactory.getRepository(UserRepository.class);
    }

    public void destroyEntityManager(@Disposes EntityManager entityManager) {
        entityManager.close();
    }

    public void destroyEntityManagerFactory(@Disposes EntityManagerFactory entityManagerFactory) { entityManagerFactory.close(); }
}
