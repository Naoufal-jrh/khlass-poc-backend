package com.poc.khlassmvp.mapper.impl;


import com.poc.khlassmvp.dtos.ReceiptDto;
import com.poc.khlassmvp.entities.ReceiptEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptMapper implements Mapper<ReceiptEntity, ReceiptDto> {
    private final ModelMapper modelMapper;

    @Override
    public ReceiptEntity toEntity(ReceiptDto dto) {
        return modelMapper.map(dto, ReceiptEntity.class);
    }

    @Override
    public ReceiptDto toDto(ReceiptEntity entity) {
        return modelMapper.map(entity, ReceiptDto.class);
    }
}
