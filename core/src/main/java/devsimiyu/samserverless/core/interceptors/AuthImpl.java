package devsimiyu.samserverless.core.interceptors;

import devsimiyu.samserverless.core.security.Principal;
import jakarta.annotation.security.RolesAllowed;
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
        List<String> role = Arrays.asList(context.getMethod().getAnnotation(RolesAllowed.class).value());
        System.out.println("Roles Allowed: " + role);
        if (principal.getRoles().stream().noneMatch(role::contains)) throw new Exception("NOT AUTHORIZED");
        return context.proceed();
    }
}
