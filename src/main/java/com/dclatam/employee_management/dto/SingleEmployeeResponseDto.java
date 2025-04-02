package com.dclatam.employee_management.dto;

import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleEmployeeResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private EmployeeDto data;

    @JsonProperty("message")
    private String message;

    // Constructor
    public SingleEmployeeResponseDto(String status, EmployeeDto data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    // Getters y Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeDto getData() {
        return data;
    }

    public void setData(EmployeeDto data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
