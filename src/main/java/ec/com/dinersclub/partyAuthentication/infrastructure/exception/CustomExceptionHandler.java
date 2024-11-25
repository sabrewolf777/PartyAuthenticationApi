package ec.com.dinersclub.partyAuthentication.infrastructure.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	 protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    	log.info("CustomExceptionHandler: handleTypeMismatch  {}",ex);
	        String message = String.format("Invalid value %s for %s", ex.getValue(), getPropertyNameFromValue(ex, request).orElse("property"));
	        List<String> errors = new ArrayList<>();
	        errors.add(ex.getMessage());

	        Error error = new Error(HttpStatus.BAD_REQUEST, message, errors);
	        return handleExceptionInternal(ex, error, headers, error.getStatus(), request);
	    }

	    private Optional<String> getPropertyNameFromValue(TypeMismatchException ex, WebRequest request) {
	        String propertyName = ex.getPropertyName();
	        if (propertyName == null) {
	            Optional<String> value = request.getParameterMap().entrySet().stream().filter(e -> request.getParameter(e.getKey()).equals(ex.getValue())).map(e -> e.getKey()).findFirst();
	            propertyName = value.orElse(null);
	        }
	        return Optional.ofNullable(propertyName);
	    }
}