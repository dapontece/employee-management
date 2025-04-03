package com.dclatam.employee_management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalaryCalculatorService {

    /**
     * Calculates the annual salary of an employee.
     * Formula: monthly salary * 12
     *
     * @param monthlySalary Employee's monthly salary.
     * @return Calculated annual salary or null if the input is invalid.
     */
    public Double calculateAnnualSalary(Double monthlySalary) {
        if (monthlySalary == null) {
            log.info("Monthly salary is null. Returning null.");
            return null;
        }

        if (monthlySalary < 0) {
            log.info("Invalid monthly salary: {}. Salary must be non-negative.", monthlySalary);
            return null;
        }

        double annualSalary = monthlySalary * 12;
        log.info("Calculated annual salary: {}", annualSalary);
        return annualSalary;
    }
}
