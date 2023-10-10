package devsimiyu.samserverless.core.interceptors;

import devsimiyu.samserverless.core.security.Principal;
import devsimiyu.samserverless.core.security.Role;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.Arrays;
import java.util.List;

@Interceptor
@Auth
public class AuthImpl {

    @Inject
    Principal principal;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        List<Role> role = Arrays.asList(context.getMethod().getAnnotation(Auth.class).value());
        if (principal.getRoles().stream().noneMatch(role::contains)) throw new Exception("NOT AUTHORIZED");
        return context.proceed();
    }
}
