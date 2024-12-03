package com.example.CompanyMs.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void createCompany(Company company);

    Company findCompanyById(Long id);

    Boolean removeCompanyById(Long id);

    Boolean updateCompanyById(Long id, Company company);

}
