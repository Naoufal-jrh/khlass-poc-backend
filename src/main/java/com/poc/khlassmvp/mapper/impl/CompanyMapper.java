package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.CompanyDto;
import com.poc.khlassmvp.entities.CompanyEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper implements Mapper<CompanyEntity, CompanyDto> {
    private final ModelMapper modelMapper;

    @Override
    public CompanyEntity toEntity(CompanyDto dto) {
        return modelMapper.map(dto, CompanyEntity.class);
    }

    @Override
    public CompanyDto toDto(CompanyEntity entity) {
        return modelMapper.map(entity, CompanyDto.class);
    }
}
