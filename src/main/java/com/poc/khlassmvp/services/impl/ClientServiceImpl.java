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
        return clientRepository
                        .findById(id)
                        .map(clientMapper::toDto)
                        .orElseThrow(() -> new NoSuchElementException("Client with id " + id + " not found"));
    }

    // TODO : check if category exists
    @Override
    public List<ClientDto> getClientsByCategoryId(Long id) {
        return clientRepository.findAllByCategoryId(id).stream().map(clientMapper::toDto).toList();
    }

    // TODO : check if company exists
    @Override
    public List<ClientDto> getClientsByCompanyId(Long id) {
        return clientRepository.findAllByCategory_Company_Id(id).stream().map(clientMapper::toDto).toList();
    }

    // TODO : add a list of clients
    /* TODO : reinforce business logic
    payment identifier : this should be generated based on the organization + category + client (school 1 + category 3 + client 23) => ORG1CAT3CL23
    name : should not be null or empty
    phone : can't be empty, check the regex (+212 x xx xx xx xx)
    email : can be empty, if not check email regex
    address : can be empty
    category : can not be null
     */
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

        // TODO : payment identifier : this should be generated based on the organization + category + client (school 1 + category 3 + client 23) => ORG1CAT3CL23
        if (clientEntity.getPaymentIdentifier() != null) {
            existingClient.setPaymentIdentifier(clientEntity.getPaymentIdentifier());
        }
        // TODO : name : should not be null or empty
        if (clientEntity.getName() != null) {
            existingClient.setName(clientEntity.getName());
        }
        // TODO : phone : can't be empty, check the regex (+212 x xx xx xx xx)
        if (clientEntity.getPhone() != null) {
            existingClient.setPhone(clientEntity.getPhone());
        }
        // TODO : email : can be empty, if not check email regex
        if (clientEntity.getEmail() != null) {
            existingClient.setEmail(clientEntity.getEmail());
        }
        // TODO : address : can be empty
        if (clientEntity.getAddress() != null) {
            existingClient.setAddress(clientEntity.getAddress());
        }
        // TODO : category : can not be null
        if (clientEntity.getCategory() != null) {
            existingClient.setCategory(clientEntity.getCategory());
        }

        return clientMapper.toDto(clientRepository.save(existingClient));
    }

    // TODO : only soft delete this, add a flag in the client table that indicates if this is deleted or not.
    @Override
    public void deleteClientById(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id " + id));

        clientRepository.delete(client);
    }
}
