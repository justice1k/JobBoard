package com.justice1k.JobBoard.Company;

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

//    Getting
    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

//    Adding a company
    @PostMapping
    public void addCompany(@RequestBody Company company){
        companyService.createCompany(company);
    }


//    Updating company Info
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,  @RequestBody Company company){
        if (companyService.updateCompany(id,company))
            return new ResponseEntity<>("Company updated", HttpStatus.OK);
        return new ResponseEntity<>("Company not updated", HttpStatus.NOT_FOUND);
    }

//    Deleting a company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(Long id){
        if (companyService.deleteCompany(id))
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        return new ResponseEntity<>("Company not deleted", HttpStatus.NOT_FOUND);
    }




}
