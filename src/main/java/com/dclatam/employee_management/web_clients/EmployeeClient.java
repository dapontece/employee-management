package com.dclatam.employee_management.web_clients;


import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeClient {

    Mono<EmployeeResponseDto> getAllEmployees();

    Mono<EmployeeDto> getEmployeeById(Integer id);
}
