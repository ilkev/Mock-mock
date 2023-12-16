package com.example.collections.service;

import com.example.collections.exception.EmployeeAlreadyAddedException;
import com.example.collections.exception.EmployeeNotFoundException;
import com.example.collections.exception.EmployeeStorageIsFullException;
import com.example.collections.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MAX_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);

    public EmployeeService() {

    }

    public Employee add(Employee employee) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    private static String createKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    public static double getScale() {
        double start = 10000;
        double end = 20000;
        return (double) Math.round((Math.random() * (end - start)) + start);
    }
}
