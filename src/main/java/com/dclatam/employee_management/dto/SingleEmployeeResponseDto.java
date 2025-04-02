package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SingleEmployeeResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private EmployeeDto data;

    @JsonProperty("message")
    private String message;


   }
