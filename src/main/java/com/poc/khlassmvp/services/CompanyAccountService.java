package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.CompanyAccountDto;
import com.poc.khlassmvp.entities.CompanyAccountEntity;

public interface CompanyAccountService {
    CompanyAccountDto getCompanyAccountById(Long id);
    CompanyAccountDto addCompanyAccount(CompanyAccountEntity companyAccountEntity);
    CompanyAccountDto updateCompanyAccount(CompanyAccountEntity companyAccountEntity);
    void deleteCompanyAccount(Long id);
}
