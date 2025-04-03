package com.dclatam.employee_management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryCalculatorServiceTest {

    private SalaryCalculatorService salaryCalculatorService;

    @BeforeEach
    void setUp() {
        salaryCalculatorService = new SalaryCalculatorService();
    }

    @Test
    void testCalculateAnnualSalary_ValidSalary() {
        Double monthlySalary = 3000.0;
        Double expectedAnnualSalary = 3000.0 * 12;

        Double result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

        assertNotNull(result);
        assertEquals(expectedAnnualSalary, result);
    }

    @Test
    void testCalculateAnnualSalary_NullSalary() {
        Double result = salaryCalculatorService.calculateAnnualSalary(null);
        assertNull(result);
    }

    @Test
    void testCalculateAnnualSalary_NegativeSalary() {
        Double result = salaryCalculatorService.calculateAnnualSalary(-1000.0);
        assertNull(result);
    }

    @Test
    void testCalculateAnnualSalary_ZeroSalary() {
        Double result = salaryCalculatorService.calculateAnnualSalary(0.0);
        assertNotNull(result);
        assertEquals(0.0, result);
    }
}
