package com.tw.apistackbase.controller;

import com.tw.apistackbase.Employee;
import com.tw.apistackbase.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity createEmployees(@RequestBody Employee employee) {
        employee.setId(1);
        employeeRepository.add(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity getEmployees() {
        return ResponseEntity.ok(employeeRepository.getEmployees());
    }

}
