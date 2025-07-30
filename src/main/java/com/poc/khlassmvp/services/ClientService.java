package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(Long id);
    List<ClientDto> getClientsByCategoryId(Long id);
    List<ClientDto> getClientsByCompanyId(Long id);
    ClientDto addClient(ClientEntity clientEntity);
    ClientDto updateClient(ClientEntity clientEntity, Long clientId);
    void deleteClientById(Long id);
}
