package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseDto {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<EmployeeDto> data;

    @JsonProperty("message")
    private String message;
}