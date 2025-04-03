package com.dclatam.employee_management.service.employee;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeService {

    Mono<List<EmployeeDto>> getAllEmployees();

    Mono<EmployeeDto> getEmployeeById(Integer id);

}
