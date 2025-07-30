package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.CompanyDto;
import com.poc.khlassmvp.entities.CompanyEntity;
import com.poc.khlassmvp.mapper.impl.CompanyMapper;
import com.poc.khlassmvp.repositories.CompanyRepository;
import com.poc.khlassmvp.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDto getCompanyById(Long id) {
        return companyRepository.findById(id).map(companyMapper::toDto).orElse(null);
    }

    @Override
    public CompanyDto addCompany(CompanyEntity companyEntity) {
        return companyMapper.toDto(companyRepository.save(companyEntity));
    }

    @Override
    public CompanyDto updateCompany(CompanyEntity companyEntity) {
        return companyMapper.toDto(companyRepository.save(companyEntity));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
