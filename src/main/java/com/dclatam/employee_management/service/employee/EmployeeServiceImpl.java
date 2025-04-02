package com.dclatam.employee_management.service.employee;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.dclatam.employee_management.service.SalaryCalculatorService;
import com.dclatam.employee_management.web_clients.EmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeClient employeeClient;
    private final SalaryCalculatorService salaryCalculatorService;

    @Override
    public Mono<List<EmployeeDto>> getAllEmployees() {
        log.info("Fetching all employees...");
        return employeeClient.getAllEmployees()
                .map(response -> {
                    List<EmployeeDto> employees = response.getData().stream()
                            .map(employee -> {
                                employee.setAnnualSalary(salaryCalculatorService.calculateAnnualSalary(employee.getSalary()));
                                return employee;
                            })
                            .collect(Collectors.toList());
                    log.info("Retrieved {} employees successfully.", employees.size());
                    return employees;
                });
    }


    @Override
    public Mono<EmployeeDto> getEmployeeById(Integer id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeClient.getEmployeeById(id)
                .map(employee -> {
                    employee.setAnnualSalary(salaryCalculatorService.calculateAnnualSalary(employee.getSalary()));
                    log.info("Successfully retrieved employee ID: {}", id);
                    return employee;
                });
    }
}
