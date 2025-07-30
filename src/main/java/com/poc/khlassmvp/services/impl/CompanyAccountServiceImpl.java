package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.CompanyAccountDto;
import com.poc.khlassmvp.entities.CompanyAccountEntity;
import com.poc.khlassmvp.mapper.impl.CompanyAccountMapper;
import com.poc.khlassmvp.mapper.impl.CompanyMapper;
import com.poc.khlassmvp.repositories.CompanyAccountRepository;
import com.poc.khlassmvp.repositories.CompanyRepository;
import com.poc.khlassmvp.services.CompanyAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyAccountServiceImpl implements CompanyAccountService {
    private final CompanyAccountRepository companyAccountRepository;
    private final CompanyAccountMapper companyAccountMapper;

    @Override
    public CompanyAccountDto getCompanyAccountById(Long id) {
        return companyAccountRepository.findById(id).map(companyAccountMapper::toDto).orElse(null);
    }

    @Override
    public CompanyAccountDto addCompanyAccount(CompanyAccountEntity companyAccountEntity) {
        return companyAccountMapper.toDto(companyAccountRepository.save(companyAccountEntity));
    }

    @Override
    public CompanyAccountDto updateCompanyAccount(CompanyAccountEntity companyAccountEntity) {
        return companyAccountMapper.toDto(companyAccountRepository.save(companyAccountEntity));
    }

    @Override
    public void deleteCompanyAccount(Long id) {
        companyAccountRepository.deleteById(id);
    }
}
