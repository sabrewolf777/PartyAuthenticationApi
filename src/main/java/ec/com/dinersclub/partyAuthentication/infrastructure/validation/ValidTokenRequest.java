package ec.com.dinersclub.partyAuthentication.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TokenRequestValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTokenRequest {
    String message() default "Invalid token request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}