package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.CompanyAccountDto;
import com.poc.khlassmvp.entities.CompanyAccountEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyAccountMapper implements Mapper<CompanyAccountEntity, CompanyAccountDto> {
    private final ModelMapper modelMapper;


    @Override
    public CompanyAccountEntity toEntity(CompanyAccountDto dto) {
        return modelMapper.map(dto, CompanyAccountEntity.class);
    }

    @Override
    public CompanyAccountDto toDto(CompanyAccountEntity entity) {
        return modelMapper.map(entity, CompanyAccountDto.class);
    }
}
