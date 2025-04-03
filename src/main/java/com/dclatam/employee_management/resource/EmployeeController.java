package com.dclatam.employee_management.resource;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Controller for managing employees.
 * Provides endpoints to retrieve employee information.
 */
@RestController
@RequestMapping("${controller.base-path}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public Mono<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees()
                .map(employees -> new EmployeeResponseDto("success", employees, "Employees retrieved successfully"));
    }

    @GetMapping("/{id}")
    public Mono<EmployeeResponseDto> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> new EmployeeResponseDto("success", List.of(employee), "Employee retrieved successfully"));
    }


}
