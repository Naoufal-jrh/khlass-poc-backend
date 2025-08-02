package com.poc.khlassmvp.controllers;

import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.mapper.impl.ClientMapper;
import com.poc.khlassmvp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam(required = false) Long companyId, @RequestParam(required = false) Long categoryId ) {
        List<ClientDto> clients;
        if (companyId != null) {
            clients = clientService.getClientsByCompanyId(companyId);
        } else if (categoryId != null) {
            clients = clientService.getClientsByCategoryId(categoryId);
        } else {
            clients = new ArrayList<>();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId) {
        // TODO: handle this with AOP controller advice
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(clientService.getClientById(clientId));
        } catch (NoSuchElementException nse){
            return ResponseEntity.notFound().build();
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        try{
            ClientDto client = clientService.addClient(clientMapper.toEntity(clientDto));
            return ResponseEntity.created(URI.create("/client/"+client.getId())).body(client);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("{clientId}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto) {
        try{
            ClientDto client = clientService.updateClient(clientMapper.toEntity(clientDto), clientId);
            return ResponseEntity.created(URI.create("/client/"+client.getId())).body(client);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        try{
            clientService.deleteClientById(clientId);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException nse){
            return ResponseEntity.notFound().build();
        }
    }

}
