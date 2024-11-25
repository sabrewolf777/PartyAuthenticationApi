package ec.com.dinersclub.partyAuthentication.infrastructure.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String INTERNAL_ERROR_CUSTOM_MESSAGE ="Error de lado del servidor";
    private static final String MENSAJE_ERROR_GENERAL="exception desde: GlobalExceptionHandler: {}";
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    	log.info("GlobalExceptionHandler: {}",ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.put(
                violation.getPropertyPath().toString(),
                violation.getMessage()
            );
        });
        
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)//500
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "error de lado del servidor")
    public void everyException(Exception e) {
        log.info(MENSAJE_ERROR_GENERAL,INTERNAL_ERROR_CUSTOM_MESSAGE);
    }
    
    @ExceptionHandler(NotFoundException.class)//404
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "recurso no encontrado")
    public void everyException(NotFoundException e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(Unauthorized.class)//401
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "recurso sin autorización")
    public void everyException(Unauthorized e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(ForbiddenException.class)//403
    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "recurso no encontrado")
    public void everyException(ForbiddenException e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(BadRequestException.class)//400
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "request mal implementado")
    public void everyException(BadRequestException e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    
    @ExceptionHandler(java.util.concurrent.TimeoutException.class)//408
    @ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "excedio el tiempo de la petición")
    public void everyException(java.util.concurrent.TimeoutException e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
} 