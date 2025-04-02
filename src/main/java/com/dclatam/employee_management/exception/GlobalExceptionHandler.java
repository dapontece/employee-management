package com.dclatam.employee_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Map<String, Object>> handleWebClientResponseException(WebClientResponseException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getResponseBodyAsString());
        response.put("code", ex.getStatusCode().value());

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        response.put("code", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "An unexpected error occurred");
        response.put("details", ex.getMessage());
        response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Manejo de Too Many Requests (429)
    @ExceptionHandler(WebClientResponseException.TooManyRequests.class)
    public ResponseEntity<Map<String, Object>> handleTooManyRequestsException(WebClientResponseException.TooManyRequests ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());
        response.put("code", HttpStatus.TOO_MANY_REQUESTS.value());

        return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(RuntimeException ex) {
        if (ex.getMessage().contains("Employee with ID")) { // Verificamos si es un error de empleado no encontrado
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.NOT_FOUND.value());
            response.put("status", "error");
            response.put("message", ex.getMessage());
            response.put("details", "The requested employee ID was not found in the DATA.");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return handleGlobalException(ex);
    }

}
