package com.dclatam.employee_management.web_clients;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.SingleEmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final WebClient.Builder webClientBuilder;
    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1";

    public EmployeeClientImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    private WebClient getWebClient() {
        return webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Override
    public Mono<EmployeeResponseDto> getAllEmployees() {
        return getWebClient()
                .get()
                .uri("/employees")
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class);
    }

    @Override
    public Mono<EmployeeDto> getEmployeeById(Integer id) {
        return getWebClient()
                .get()
                .uri("/employee/{id}", id)
                .retrieve()
                .bodyToMono(SingleEmployeeResponseDto.class) // Mapear correctamente la estructura
                .map(SingleEmployeeResponseDto::getData); // Extraer solo el objeto EmployeeDto
    }
}
