package devsimiyu.samserverless.core.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

@Interceptor
@Transactional
public class TransactionalImpl {

    @Inject
    private UserTransaction userTransaction;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        try {
            userTransaction.begin();
            Object result = context.proceed();
            userTransaction.commit();
            return result;
        }
        catch (Exception e) {
            try { userTransaction.rollback(); }
            catch (Exception rollbackException) { System.err.println("Failed to rollback: " + e); }
            throw e;
        }
    }
}
