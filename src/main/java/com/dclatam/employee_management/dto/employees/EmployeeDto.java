package com.dclatam.employee_management.dto.employees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private Double salary;

    // Getter nuevo
    @JsonProperty("employee_anual_salary")
    private Double annualSalary;

    @JsonProperty("employee_age")
    private Integer age;

    @JsonProperty("profile_image")
    private String profileImage;


}
