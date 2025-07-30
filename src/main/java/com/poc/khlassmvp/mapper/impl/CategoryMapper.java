package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.CategoryDto;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<CategoryEntity, CategoryDto> {
    private final ModelMapper modelMapper;

    @Override
    public CategoryEntity toEntity(CategoryDto dto) {
        return modelMapper.map(dto, CategoryEntity.class);
    }

    @Override
    public CategoryDto toDto(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }
}
