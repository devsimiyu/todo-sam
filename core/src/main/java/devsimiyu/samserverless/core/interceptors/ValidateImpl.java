package devsimiyu.samserverless.core.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.HashSet;
import java.util.Set;

@Interceptor
@Validate
public class ValidateImpl {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
        for (Object param: context.getParameters()) {
            violations.addAll(validator.validate(param));
        }
        System.out.println("Found User Violations: " + violations);
        if (!violations.isEmpty()) throw new Error("FAILED USER VALIDATION");
        return context.proceed();
    }
}
