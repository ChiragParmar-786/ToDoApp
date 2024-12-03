package com.example.CompanyMs.Company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean removeCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateCompanyById(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if(optionalCompany.isPresent())
        {
            Company companyToUpdate = optionalCompany.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }
}
