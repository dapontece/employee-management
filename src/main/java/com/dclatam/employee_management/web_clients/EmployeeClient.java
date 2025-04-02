package com.dclatam.employee_management.web_clients;


import com.dclatam.employee_management.dto.employees.EmployeeDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeClient {

    Mono<List<EmployeeDto>> getAllEmployees();

    Mono<EmployeeDto> getEmployeeById(Integer id);
}
