package com.example.CompanyMs.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
       Company company = companyService.findCompanyById(id);
       if(company!=null)
           return new ResponseEntity<>(company,HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        Boolean sts = companyService.removeCompanyById(id);

        if(sts)
            return new ResponseEntity<>("Company is deleted successfully ",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id,@RequestBody Company company){
        Boolean sts = companyService.updateCompanyById(id,company);

        if(sts)
            return new ResponseEntity<>("Company is updated successfully ",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company not Found",HttpStatus.NOT_FOUND);
    }

}
