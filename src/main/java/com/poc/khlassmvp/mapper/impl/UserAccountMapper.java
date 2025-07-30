package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.UserAccountDto;
import com.poc.khlassmvp.entities.UserAccountEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountMapper implements Mapper<UserAccountEntity, UserAccountDto> {
    private final ModelMapper modelMapper;

    @Override
    public UserAccountEntity toEntity(UserAccountDto dto) {
        return modelMapper.map(dto, UserAccountEntity.class);
    }

    @Override
    public UserAccountDto toDto(UserAccountEntity entity) {
        return modelMapper.map(entity, UserAccountDto.class);
    }
}
