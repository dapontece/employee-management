package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor // 👈 Esto genera un constructor vacío
@AllArgsConstructor
public class EmployeeResponseDto {

    // Getter y Setter manuales para 'status'
    @JsonProperty("status")
    private String status;

    // Getter y Setter manuales para 'data'
    @JsonProperty("data")
    private List<EmployeeDto> data;

    // Getter y Setter manuales para 'message'
    @JsonProperty("message")
    private String message;


}
