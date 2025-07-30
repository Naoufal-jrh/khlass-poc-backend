package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.mapper.impl.ClientMapper;
import com.poc.khlassmvp.repositories.ClientRepository;
import com.poc.khlassmvp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto getClientById(Long id) {
        return clientMapper.toDto(clientRepository.findById(id).orElse(null));
    }

    @Override
    public List<ClientDto> getClientsByCategoryId(Long id) {
        return clientRepository.findAllByCategoryId(id).stream().map(clientMapper::toDto).toList();
    }

    @Override
    public List<ClientDto> getClientsByCompanyId(Long id) {
        return clientRepository.findAllByCompanyId(id).stream().map(clientMapper::toDto).toList();
    }

    @Override
    public ClientDto addClient(ClientEntity clientEntity) {
        return clientMapper.toDto(clientRepository.save(clientEntity));
    }

    @Override
    public ClientDto updateClient(ClientEntity clientEntity, Long clientId) {
        clientEntity.setId(clientId);
        return clientMapper.toDto(clientRepository.save(clientEntity));
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
