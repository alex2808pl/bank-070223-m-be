package de.telran.bank.controller;

import de.telran.bank.dto.ClientDto;
import de.telran.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    ClientService clientService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> getClientId(@PathVariable Long id) {
        ClientDto client = clientService.getClientId(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

//    @ResponseStatus( HttpStatus.OK)
//    public ClientDto getClientId1(@PathVariable Long id) {
//        ClientDto client = clientService.getClientId(id);
//        return client;
//    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        ClientDto client = clientService.createClient(clientDto);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
        ClientDto client = clientService.updateClient(clientDto);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
