package ec.com.dinersclub.partyAuthentication.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpHeaders;

public class HeadersValidator implements ConstraintValidator<ValidHeaders, HttpHeaders> {

    @Override
    public void initialize(ValidHeaders constraintAnnotation) {
    }

    @Override
    public boolean isValid(HttpHeaders headers, ConstraintValidatorContext context) {
    	
        if (headers == null) {
            addConstraintViolation(context, "Los headers son requeridos");
            return false;
        }

        // Validar headers requeridos
        if (!headers.containsKey("applicationId")) {
            addConstraintViolation(context, "El header applicationId es requerido");
            return false;
        }

        if (!headers.containsKey("channelId")) {
            addConstraintViolation(context, "El header channelId es requerido");
            return false;
        }
        
        if (!headers.containsKey("uuid")) {
            addConstraintViolation(context, "El header uuid es requerido");
            return false;
        }
        
        if (!headers.containsKey("ipaddress")) {
            addConstraintViolation(context, "El header ipaddress es requerido");
            return false;
        }
        
        if (!headers.containsKey("transactionDate")) {
            addConstraintViolation(context, "El header transactionDate es requerido");
            return false;
        }
        
        if (!headers.containsKey("simetricKey")) {
            addConstraintViolation(context, "El header simetricKey es requerido");
            return false;
        }
        
        if (!headers.containsKey("recordsAmount")) {
            addConstraintViolation(context, "El header recordsAmount es requerido");
            return false;
        }
        
        if (!headers.containsKey("pagesAmount")) {
            addConstraintViolation(context, "El header pagesAmount es requerido");
            return false;
        }
        
        if (!headers.containsKey("pagesCurrentIndex")) {
            addConstraintViolation(context, "El header pagesCurrentIndex es requerido");
            return false;
        }
        
        /* Validar formato del token Bearer
        String authHeader = headers.getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            addConstraintViolation(context, "El formato del token de autorización es inválido");
            return false;
        }
        */


        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
} 