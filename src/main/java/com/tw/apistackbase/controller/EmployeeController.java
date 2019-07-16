package com.tw.apistackbase.controller;

import com.tw.apistackbase.Employee;
import com.tw.apistackbase.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int pageSize) {
        if (page > 0 && pageSize > 0) {
            return ResponseEntity.ok(employeeRepository.getEmployees().subList(0, page * pageSize));
        }
        return ResponseEntity.ok(employeeRepository.getEmployees());
    }
}
