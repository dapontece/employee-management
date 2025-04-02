package com.dclatam.employee_management.service.employee;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.dclatam.employee_management.service.SalaryCalculatorService;
import com.dclatam.employee_management.web_clients.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeClient employeeClient;
    private final SalaryCalculatorService salaryCalculatorService;

    @Override
    public Mono<List<EmployeeDto>> getAllEmployees() {
        return employeeClient.getAllEmployees()
                .map(response -> response.getData().stream().map(employee -> {
                    employee.setAnnualSalary(salaryCalculatorService.calculateAnnualSalary(employee.getSalary()));
                    return employee;
                }).collect(Collectors.toList()));
    }


    @Override
    public Mono<EmployeeDto> getEmployeeById(Integer id) {
        return employeeClient.getEmployeeById(id)
                .map(employee -> {
                    employee.setAnnualSalary(salaryCalculatorService.calculateAnnualSalary(employee.getSalary()));
                    return employee;
                });
    }
}
