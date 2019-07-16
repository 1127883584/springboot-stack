package com.tw.apistackbase.controller;

import com.tw.apistackbase.Company;
import com.tw.apistackbase.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/companies")
    public ResponseEntity createCompany(@RequestBody Company company) {
        companyRepository.add(company);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/companies")
    public ResponseEntity getCompanies() {
        return ResponseEntity.ok(companyRepository.getCompanies());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity getCompanyById(@PathVariable int id) {
        Company company = companyRepository.getCompanies().stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok(company);
    }
}
