package devsimiyu.samserverless.core.interceptors;

import devsimiyu.samserverless.core.security.Principal;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InterceptorBinding;
import jakarta.interceptor.InvocationContext;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface Auth {
    public static final class Literal extends AnnotationLiteral<Auth> implements Auth {
        public static final Literal INSTANCE = new Literal();
        private static final long serialVersionUID = 1L;

        public Literal() {
        }
    }
}
