package com.dclatam.employee_management.dto.employees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private Double salary;

    @JsonProperty("employee_age")
    private Integer age;

    @JsonProperty("profile_image")
    private String profileImage;

}
