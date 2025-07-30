package com.poc.khlassmvp.mapper.impl;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper implements Mapper<ClientEntity, ClientDto> {
    private final ModelMapper modelMapper;


    @Override
    public ClientEntity toEntity(ClientDto dto) {
        return modelMapper.map(dto, ClientEntity.class);
    }

    @Override
    public ClientDto toDto(ClientEntity entity) {
        return modelMapper.map(entity, ClientDto.class);
    }
}
