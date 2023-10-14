package devsimiyu.samserverless.core.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Transaction extends AbstractJtaPlatform {

    @Override
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected TransactionManager locateTransactionManager() {
       return new UserTransactionManager();
    }

    @Override
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected UserTransaction locateUserTransaction() {
        return new UserTransactionImp();
    }
}
