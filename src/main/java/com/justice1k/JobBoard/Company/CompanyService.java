package com.justice1k.JobBoard.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAll();

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company company);

    void createCompany(Company company);

    boolean deleteCompany(Long id);



}
