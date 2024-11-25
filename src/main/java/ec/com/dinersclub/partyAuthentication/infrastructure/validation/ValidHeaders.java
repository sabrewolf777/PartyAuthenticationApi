package ec.com.dinersclub.partyAuthentication.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HeadersValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHeaders {
    String message() default "Headers inválidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 