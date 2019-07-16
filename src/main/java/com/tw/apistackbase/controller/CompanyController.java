package com.tw.apistackbase.controller;

import com.tw.apistackbase.Company;
import com.tw.apistackbase.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
