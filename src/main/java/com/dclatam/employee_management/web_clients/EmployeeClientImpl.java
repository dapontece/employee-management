package com.dclatam.employee_management.web_clients;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final WebClient webClient;
    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1";

    public EmployeeClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }


    /**
     * Obtiene la lista de empleados desde la API externa.
     * @return Lista de EmployeeDto en un Mono.
     */
    @Override
    public Mono<List<EmployeeDto>> getAllEmployees() {
        return webClient.get()
                .uri("/employees")
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class)
                .map(EmployeeResponseDto::getData);
    }

    /**
     * Obtiene un empleado por ID desde la API externa.
     * @param id ID del empleado.
     * @return Mono con EmployeeDto.
     */
    @Override
    public Mono<EmployeeDto> getEmployeeById(Integer id) {
        return webClient.get()
                .uri("/employee/{id}", id)
                .retrieve()
                .bodyToMono(EmployeeDto.class);
    }
}
