package com.poc.khlassmvp.controllers;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.mapper.impl.ClientMapper;
import com.poc.khlassmvp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("company/{companyId}")
    public List<ClientDto> getAllClientsByCompanyId(@PathVariable Long companyId) {
        return clientService.getClientsByCompanyId(companyId);
    }
    @GetMapping("/{clientId}")
    public ClientDto getClientById(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }
    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientService.addClient(clientMapper.toEntity(clientDto));
    }
    @PutMapping("{clientId}")
    public ClientDto updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientMapper.toEntity(clientDto), clientId);
    }
    @DeleteMapping("{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
    }



}
