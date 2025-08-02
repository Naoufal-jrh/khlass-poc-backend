package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.entities.ClientEntity;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.mapper.impl.ClientMapper;
import com.poc.khlassmvp.repositories.ClientRepository;
import com.poc.khlassmvp.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto getClientById(Long id) throws NoSuchElementException {
        return clientMapper.toDto(
                clientRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Client with id " + id + " not found"))
        );
    }

    @Override
    public List<ClientDto> getClientsByCategoryId(Long id) {
        return clientRepository.findAllByCategoryId(id).stream().map(clientMapper::toDto).toList();
    }

    @Override
    public List<ClientDto> getClientsByCompanyId(Long id) {
        return clientRepository.findAllByCategory_Company_Id(id).stream().map(clientMapper::toDto).toList();
    }

    @Override
    public ClientDto addClient(ClientEntity clientEntity) throws IllegalArgumentException{
        boolean exists = clientRepository.existsById(clientEntity.getId());
        if (exists)
            throw new IllegalArgumentException("Client with id " + clientEntity.getId() + " already exists");
        else
            return clientMapper.toDto(clientRepository.save(clientEntity));
    }

    @Override
    public ClientDto updateClient(ClientEntity clientEntity, Long clientId) {
        ClientEntity existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id " + clientId));

        if (clientEntity.getPaymentIdentifier() != null) {
            existingClient.setPaymentIdentifier(clientEntity.getPaymentIdentifier());
        }
        if (clientEntity.getName() != null) {
            existingClient.setName(clientEntity.getName());
        }
        if (clientEntity.getPhone() != null) {
            existingClient.setPhone(clientEntity.getPhone());
        }
        if (clientEntity.getEmail() != null) {
            existingClient.setEmail(clientEntity.getEmail());
        }
        if (clientEntity.getAddress() != null) {
            existingClient.setAddress(clientEntity.getAddress());
        }
        if (clientEntity.getCategory() != null) {
            existingClient.setCategory(clientEntity.getCategory());
        }

        return clientMapper.toDto(clientRepository.save(existingClient));
    }

    @Override
    public void deleteClientById(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id " + id));

        clientRepository.delete(client);
    }
}
