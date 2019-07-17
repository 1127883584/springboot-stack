package com.tw.apistackbase.controller;

import com.tw.apistackbase.Employee;
import com.tw.apistackbase.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int pageSize,
            @RequestParam(defaultValue = "all") String gender) {
        List<Employee> employees = employeeRepository.getEmployees();
        if (page > 0 && pageSize > 0) {
            employees = employees.subList(0, page * pageSize);
        }
        if (!gender.equals("all")) {
            employees = employeeRepository.getEmployees().stream()
                    .filter(element -> element.getGender().equals(gender))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(employees);
        }
        return ResponseEntity.ok(employeeRepository.getEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.getEmployees().stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        Employee employee1Temp = new Employee();
        BeanUtils.copyProperties(employee, employee1Temp);
        employee1Temp.setId(employeeRepository.getEmployees().size() + 1);
        employeeRepository.getEmployees().add(employee1Temp);
        return ResponseEntity.ok(employee1Temp);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee updateEmployee = employeeRepository.getEmployees().stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);

        BeanUtils.copyProperties(employee, updateEmployee);
        updateEmployee.setId(id);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        List<Employee> afterDeleteEmployee = employeeRepository.getEmployees().stream()
                .filter(element -> element.getId() != id)
                .collect(Collectors.toList());

        return ResponseEntity.ok(afterDeleteEmployee);
    }
}
