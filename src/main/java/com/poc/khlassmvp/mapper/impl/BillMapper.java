package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.BillDto;
import com.poc.khlassmvp.entities.BillEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillMapper implements Mapper<BillEntity, BillDto> {
    private final ModelMapper modelMapper;

    @Override
    public BillEntity toEntity(BillDto dto) {
        return modelMapper.map(dto, BillEntity.class);
    }

    @Override
    public BillDto toDto(BillEntity entity) {
        return modelMapper.map(entity, BillDto.class);
    }
}
