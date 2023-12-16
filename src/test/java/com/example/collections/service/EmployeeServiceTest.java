package com.example.collections.service;

import com.example.collections.exception.EmployeeAlreadyAddedException;
import com.example.collections.exception.EmployeeNotFoundException;
import com.example.collections.exception.EmployeeStorageIsFullException;
import com.example.collections.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullException() {
        for (int i = 0; i < 5; i++) {
            char addChar = (char) ('a' + i);
            Employee employee = new Employee("name" + addChar, "lastName", 1, 1000);
            employeeService.add(employee);
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(new Employee("Danil", "Bogomolov", 1, 1000))
        );
    }

    @Test
    void uniqueVerification() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add(new Employee("name", "last_name", 1, 1))
        );
    }

    @Test
    void employeeNotFound() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find("name2", "last_name")
        );
    }

    @Test
    void employeeCantBeRemoved() {
        Employee employee = new Employee("name", "last_name", 1, 1);
        employeeService.add(employee);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.remove("name2", "last_name")
        );
    }
}
