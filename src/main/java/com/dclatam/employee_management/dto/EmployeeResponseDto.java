package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<EmployeeDto> data;

    @JsonProperty("message")
    private String message;


}
