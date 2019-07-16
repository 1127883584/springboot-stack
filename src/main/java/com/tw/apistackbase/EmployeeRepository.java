package com.tw.apistackbase;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository {
    private List<Employee> employees;

    public EmployeeRepository() {
        this.employees = new ArrayList<>();
        employees.add(new Employee(1, "zhangsan", 18, "male", 5000));
        employees.add(new Employee(2, "lisi", 25, "female", 7000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }
}
