package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmployeeResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<EmployeeDto> data;

    @JsonProperty("message")
    private String message;

    // Getter y Setter manuales para 'status'
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter y Setter manuales para 'data'
    public List<EmployeeDto> getData() {
        return data;
    }

    public void setData(List<EmployeeDto> data) {
        this.data = data;
    }

    // Getter y Setter manuales para 'message'
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
