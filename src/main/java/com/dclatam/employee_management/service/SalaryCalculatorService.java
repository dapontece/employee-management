package com.dclatam.employee_management.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryCalculatorService {

    /**
     * Calcula el salario anual de un empleado.
     * Fórmula: salario mensual * 12
     * @param monthlySalary Salario mensual del empleado.
     * @return Salario anual calculado.
     */
    public Double calculateAnnualSalary(Double monthlySalary) {
        return (monthlySalary != null) ? monthlySalary * 12 : null;
    }
}