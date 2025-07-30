package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.CompanyDto;
import com.poc.khlassmvp.entities.CompanyEntity;

public interface CompanyService {
    CompanyDto getCompanyById(Long id);
    CompanyDto addCompany(CompanyEntity companyEntity);
    CompanyDto updateCompany(CompanyEntity companyEntity);
    void deleteCompany(Long id);
}
