package devsimiyu.samserverless.core.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

@ApplicationScoped
public class Transaction extends AbstractJtaPlatform {

    @Override
    protected TransactionManager locateTransactionManager() {
       return new UserTransactionManager();
    }

    @Override
    @Produces
    protected UserTransaction locateUserTransaction() {
        return new UserTransactionImp();
    }
}
