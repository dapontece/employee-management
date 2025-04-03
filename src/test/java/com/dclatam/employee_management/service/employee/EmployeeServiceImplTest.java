package com.dclatam.employee_management.service.employee;

import com.dclatam.employee_management.dto.EmployeeResponseDto;
import com.dclatam.employee_management.dto.employees.EmployeeDto;
import com.dclatam.employee_management.service.SalaryCalculatorService;
import com.dclatam.employee_management.web_clients.EmployeeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeClient employeeClient;

    @Mock
    private SalaryCalculatorService salaryCalculatorService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private List<EmployeeDto> employeeList;

    @BeforeEach
    void setUp() {
        // Simulación de empleados
        EmployeeDto employee1 = new EmployeeDto(1, "John Doe", 3000.0, 0.0, 30, "image1.jpg");
        EmployeeDto employee2 = new EmployeeDto(2, "Jane Doe", 4000.0, 0.0, 28, "image2.jpg");
        employeeList = List.of(employee1, employee2);

        // Mock para obtener todos los empleados
        lenient().when(employeeClient.getAllEmployees()).thenReturn(Mono.just(new EmployeeResponseDto("success", employeeList, "Employees retrieved")));
        lenient().when(salaryCalculatorService.calculateAnnualSalary(3000.0)).thenReturn(36000.0);
        lenient().when(salaryCalculatorService.calculateAnnualSalary(4000.0)).thenReturn(48000.0);

    }

    @Test
    void testGetAllEmployees() {
        // Simula la respuesta del cliente externo
        EmployeeResponseDto responseDto = new EmployeeResponseDto("success", employeeList, "Employees retrieved");

        when(employeeClient.getAllEmployees()).thenReturn(Mono.just(responseDto));

        StepVerifier.create(employeeService.getAllEmployees())
                .expectNextMatches(employees -> employees.size() == 2 &&
                        employees.get(0).getAnnualSalary() == 36000.0 &&
                        employees.get(1).getAnnualSalary() == 48000.0)
                .verifyComplete();

        verify(employeeClient, times(1)).getAllEmployees();
        verify(salaryCalculatorService, times(1)).calculateAnnualSalary(3000.0);
        verify(salaryCalculatorService, times(1)).calculateAnnualSalary(4000.0);
    }


    @Test
    void testGetEmployeeById() {
        // Simula un empleado con ID 1
        EmployeeDto employee = new EmployeeDto(1, "John Doe", 3000.0, 0.0, 30, "image1.jpg");

        // Mock del cliente externo
        lenient().when(employeeClient.getEmployeeById(1)).thenReturn(Mono.just(employee));

        // Llamar al método del servicio
        Mono<EmployeeDto> resultMono = employeeService.getEmployeeById(1);

        // Verificar el resultado
        StepVerifier.create(resultMono)
                .assertNext(result -> {
                    assertNotNull(result);
                    assertEquals(1, result.getId());
                    assertEquals("John Doe", result.getName());
                    assertEquals(3000.0, result.getSalary());
                    assertEquals(30, result.getAge());
                    assertEquals("image1.jpg", result.getProfileImage());
                })
                .verifyComplete();
    }

}
