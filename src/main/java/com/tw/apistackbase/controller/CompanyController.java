package com.tw.apistackbase.controller;

import com.tw.apistackbase.Company;
import com.tw.apistackbase.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/companies")
    public ResponseEntity createCompany(@RequestBody Company company) {
        Company companyTemp = new Company();
        BeanUtils.copyProperties(company, companyTemp);
        companyTemp.setId(companyRepository.getCompanies().size() + 1);
        companyRepository.getCompanies().add(companyTemp);
        return ResponseEntity.ok(companyTemp);
    }

    @GetMapping("/companies")
    public ResponseEntity getCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int pageSize) {
        if (page > 0 && pageSize > 0) {
            return ResponseEntity.ok(companyRepository.getCompanies().subList(0, page * pageSize));
        }
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

    @GetMapping("/company/{id}/employees")
    public ResponseEntity getEmployeesByCompanyId(@PathVariable int id) {
        Company company = companyRepository.getCompanies().stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok(company.getEmployees());
    }

    @PutMapping("/company/{id}")
    public ResponseEntity updateCompany(@PathVariable int id, @RequestBody Company company){
        Company updateCompany = companyRepository.getCompanies().stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);

        BeanUtils.copyProperties(company, updateCompany);
        updateCompany.setId(id);
        return ResponseEntity.ok(updateCompany);
    }


}
