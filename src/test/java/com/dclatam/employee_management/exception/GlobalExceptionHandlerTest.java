package com.dclatam.employee_management.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleWebClientResponseException_ShouldReturnErrorResponse() {
        // Simulamos una excepción de WebClient
        WebClientResponseException ex = mock(WebClientResponseException.class);
        when(ex.getResponseBodyAsString()).thenReturn("WebClient error occurred");
        when(ex.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        // Llamamos al método de manejo de excepción
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleWebClientResponseException(ex);

        // Validamos la respuesta
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("WebClient error occurred", response.getBody().get("message"));
    }

    @Test
    void handleIllegalArgumentException_ShouldReturnBadRequestResponse() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("Invalid argument", response.getBody().get("message"));
    }

    @Test
    void handleGlobalException_ShouldReturnInternalServerErrorResponse() {
        Exception ex = new Exception("Unexpected error");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleGlobalException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
        assertEquals("Unexpected error", response.getBody().get("details"));
    }

    @Test
    void handleTooManyRequestsException_ShouldReturnTooManyRequestsResponse() {
        WebClientResponseException.TooManyRequests ex = mock(WebClientResponseException.TooManyRequests.class);
        when(ex.getMessage()).thenReturn("Too many requests");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleTooManyRequestsException(ex);

        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("Too many requests", response.getBody().get("message"));
    }

    @Test
    void handleNotFoundException_ShouldReturnNotFoundResponse() {
        RuntimeException ex = new RuntimeException("Employee with ID 123 not found");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("Employee with ID 123 not found", response.getBody().get("message"));
        assertEquals("The requested employee ID was not found in the DATA.", response.getBody().get("details"));
    }

    @Test
    void handleNotFoundException_ShouldReturnInternalServerError_ForOtherRuntimeException() {
        RuntimeException ex = new RuntimeException("Some unexpected error");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleNotFoundException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
        assertEquals("Some unexpected error", response.getBody().get("details"));
    }
}
