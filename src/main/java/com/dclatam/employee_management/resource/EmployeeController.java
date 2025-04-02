package com.dclatam.employee_management.resource;

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

    @GetMapping
    public Mono<List<EmployeeDto>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/{id}/salary/annual")
    public Mono<Double> getEmployeeAnnualSalary(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> employeeService.calculateEmployeeAnnualSalary(employee));
    }

}
