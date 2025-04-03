package com.dclatam.employee_management.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class CorsFilterTest {

    private CorsFilter corsFilter;
    private ServletRequest request;
    private ServletResponse response;
    private FilterChain chain;
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() {
        corsFilter = new CorsFilter();
        request = mock(ServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
        httpServletResponse = (HttpServletResponse) response;
    }

    @Test
    void doFilter_ShouldSetCorsHeaders() throws ServletException, IOException {
        // Ejecutamos el filtro
        corsFilter.doFilter(request, response, chain);

        // Verificamos que los encabezados CORS se establecen correctamente
        verify(httpServletResponse).setHeader("Access-Control-Allow-Origin", "*");
        verify(httpServletResponse).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        verify(httpServletResponse).setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        // Verificamos que el filtro continúa con la cadena de filtros
        verify(chain, times(1)).doFilter(request, response);
    }
}
