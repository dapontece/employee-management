package com.dclatam.employee_management.dto.employees;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    // Constructor
    public EmployeeDto(Integer id, String name, Double salary, Integer age, String profileImage) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profileImage = profileImage;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
