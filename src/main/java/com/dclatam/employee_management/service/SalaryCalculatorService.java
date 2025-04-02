package com.dclatam.employee_management.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryCalculatorService {

    public double calculateAnnualSalary(double monthlySalary) {
        return monthlySalary * 12;
    }
}
