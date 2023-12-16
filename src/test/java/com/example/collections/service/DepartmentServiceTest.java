package com.example.collections.service;

import com.example.collections.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    //переменную в константу
    public static final Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("ivan", "ivanov", 1, 10000),
            new Employee("oleg", "olegov", 1, 15000),
            new Employee("marina", "ivanova", 2, 20000),
            new Employee("olga", "olegova", 2, 30000),
            new Employee("sergey", "sergeev", 3, 50000)
    );
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }

    @Test
    void sumTest() {
        double actual = departmentService.getDepartmentSalary(1);
        Assertions.assertEquals(25000.0, actual);
    }

    @Test
    void maxTest() {
        Double actual = departmentService.getEmployeeMaxSalary(1);
        Assertions.assertEquals(15000, actual);
    }

    @Test
    void minTest() {
        Double actual = departmentService.getEmployeeMinSalary(1);
        Assertions.assertEquals(10000, actual);
    }

    @Test
    void getAllDepTest() {
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> expected = (Collections.singletonList(new Employee("sergey", "sergeev", 3, 50000)));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getAllTest() {
        List<Employee> actual = departmentService.getAll(2);
        Collection<Employee> expected = Arrays.asList(
                new Employee("marina", "ivanova", 2, 20000),
                new Employee("olga", "olegova", 2, 30000)
        );
        Assertions.assertEquals(actual, expected);
    }
}
