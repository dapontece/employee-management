package com.dclatam.employee_management.web_clients;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.SingleEmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final WebClient webClient;

    public EmployeeClientImpl(WebClient.Builder webClientBuilder, @Value("${api.base.url}") String baseUrl) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }


    @Override
    public Mono<EmployeeResponseDto> getAllEmployees() {
        return webClient
                .get()
                .uri("/employees")
                .retrieve()
                .bodyToMono(EmployeeResponseDto.class);
    }

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
