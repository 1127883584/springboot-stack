package com.tw.apistackbase;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyRepository {
    private List<Company> companyList;

    public CompanyRepository() {
        this.companyList = new ArrayList<>();
        companyList.add(new Company(1,"alibaba", 200, new EmployeeRepository().getEmployees()));
    }

    public List<Company> getCompanies() {
        return companyList;
    }

    public void add(Company company) {
        companyList.add(company);
    }

}
