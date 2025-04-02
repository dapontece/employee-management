package com.dclatam.employee_management.resource;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.dclatam.employee_management.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint para obtener todos los empleados
    @GetMapping
    public Mono<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees()
                .map(employees -> new EmployeeResponseDto("success", employees, "Employees retrieved successfully"));
    }

    // Endpoint para obtener un empleado por ID
    @GetMapping("/{id}")
    public Mono<EmployeeResponseDto> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> new EmployeeResponseDto("success", List.of(employee), "Employee retrieved successfully"));
    }


}
