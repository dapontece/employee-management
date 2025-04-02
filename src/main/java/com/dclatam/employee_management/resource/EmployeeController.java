package com.dclatam.employee_management.resource;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.dclatam.employee_management.web_clients.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeClient employeeClient;


    @GetMapping
    public Mono<List<EmployeeDto>> getAllEmployees() {
        return employeeClient.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        return employeeClient.getEmployeeById(id);
    }
}

