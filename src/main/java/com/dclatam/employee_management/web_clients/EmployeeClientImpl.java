package com.dclatam.employee_management.web_clients;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.SingleEmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implementation of the WebClient client for communication with the employee service.
 * This class is responsible for making HTTP calls to an external service to retrieve employee information.
 */
@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final WebClient webClient;

    public EmployeeClientImpl(WebClient.Builder webClientBuilder, @Value("${api.base.url}") String baseUrl) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * Retrieves the list of all employees by calling the remote endpoint "/employees".
     *
     * @return A {@link Mono} containing the response with the list of employees.
     */
    @Override
    public Mono<EmployeeResponseDto> getAllEmployees() {
        return webClient
                .get()
                .uri("/employees")
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class);
    }

    /**
     * Retrieves employee information by its ID by calling the remote endpoint "/employee/{id}".
     *
     * @param id Employee ID to search for.
     * @return A {@link Mono} with the employee's information or an error if not found.
     */
    @Override
    public Mono<EmployeeDto> getEmployeeById(Integer id) {
        return webClient
                .get()
                .uri("/employee/{id}", id)
                .retrieve()
                .bodyToMono(SingleEmployeeResponseDto.class)
                .flatMap(response -> {
                    if (response == null || response.getData() == null) {
                        return Mono.error(new RuntimeException("Employee with ID " + id + " not found."));
                    }
                    return Mono.just(response.getData());
                });
    }
}
