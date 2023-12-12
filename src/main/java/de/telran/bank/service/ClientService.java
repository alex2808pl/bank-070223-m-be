package de.telran.bank.service;

import de.telran.bank.config.MapperUtil;
import de.telran.bank.controller.advice.ResponseException;
import de.telran.bank.dto.ClientDto;
import de.telran.bank.dto.ManagerDto;
import de.telran.bank.dto.TransactionDtoShort;
import de.telran.bank.entity.Client;
import de.telran.bank.entity.ClientStatus;
import de.telran.bank.entity.Manager;
import de.telran.bank.entity.Transaction;
import de.telran.bank.mapper.Mappers;
import de.telran.bank.repository.ClientRepository;
import de.telran.bank.repository.ManagerRepository;
import de.telran.bank.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

//    @Autowired
    private ClientRepository clientRepository;

    private ManagerRepository managerRepository;

//    @Autowired
    private Mappers mappers;

    @Autowired
    private ModelMapper modelMapper;


    public ClientService(ClientRepository clientRepository, ManagerRepository managerRepository, Mappers mappers) {
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.mappers = mappers;
    }

    public ClientDto getClientId(Long id) {
//        Client client = clientRepository.findClientById(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        ClientDto clientDto = null;
        if(clientOptional.isPresent()) {
           clientDto = MapperUtil.convertList(List.of(clientOptional.get()), mappers::convertToClientDto).get(0);
        }
        return clientDto;
    }

    public ClientDto createClient(ClientDto clientDto) throws ResponseException {
        if(clientDto!=null && clientDto.getId()==null) {
            ManagerDto managerDto = clientDto.getManagerDto();
            if(managerDto != null && managerDto.getId() != null) {
                Manager manager = managerRepository.findById(managerDto.getId()).orElse(null);
                if(manager != null) {
                    Client client = mappers.convertToClientEntity(clientDto);
                    client.setManager(manager);
                    Client clientResponse = clientRepository.save(client);
                    Long idResponse = clientResponse.getId();
                    if(idResponse != null && idResponse > 0) {
                        return mappers.convertToClientDto(clientResponse);
                    }
                    else {
                        // сообщение что создать клиента не удалось
                        throw new ResponseException("Не удалось создать клиента в БД");
                    }
                }
                else {
                    // сообщение что такого менеджера не найдено, соответственно добавить не могу
                    throw new ResponseException(String.format("Не найден менеджер с Id=%d. Не могу создать клиента!",managerDto.getId()));
                }
            }
            else {
                // сообщение о невозможности добавить
                throw new ResponseException("Отсутсвует Id связанного менеджера. Не могу создать клиента!");
            }
        }
        else {
            throw new ResponseException("Ошибка обработки полученного тела запроса!");
        }
    }

    public ClientDto updateClient(ClientDto clientDto) throws ResponseException {
        if(clientDto.getId()!=null) {
            Optional<Client> clientOptional = clientRepository.findById(clientDto.getId());
            if(clientOptional.isPresent()) {
                Client client = clientOptional.get();
                client.setStatus(clientDto.getStatus());
                client.setTaxCode(clientDto.getTaxCode());
                client.setFirstName(clientDto.getFirstName());
                client.setLastName(clientDto.getLastName());
                client.setEmail(clientDto.getEmail());
                client.setAddress(clientDto.getAddress());
                client.setPhone(clientDto.getPhone());
                client.setUpdatedAt(ZonedDateTime.now());
                Client clientResponse = clientRepository.save(client);
                if(clientResponse != null) {
                    return mappers.convertToClientDto(clientResponse);
                }
                else {
                    // сообщение что обновить клиента не удалось
                    throw new ResponseException(String.format("Не удалось обновить клиента в БД с Id=%d!",clientDto.getId()));
                }
            }
            else {
                // сообщение что не найден клиент с таким ИД в БД
                throw new ResponseException(String.format("Не найден клиент в БД с Id=%d!",clientDto.getId()));
            }
        }
        return null;
    }

    public ClientDto deleteClient(Long id) throws ResponseException {
        if(id!=null) {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if(clientOptional.isPresent()) {
                Client client = clientOptional.get();
                client.setStatus(ClientStatus.INACTIVE);
                client.setUpdatedAt(ZonedDateTime.now());
                Client clientResponse = clientRepository.save(client);
                if(clientResponse != null) {
                    return mappers.convertToClientDto(clientResponse);
                }
                else {
                    // сообщение что удалить клиента не удалось
                    throw new ResponseException(String.format("Не удалось удалить клиента в БД с Id=%d!",id));
                }
            }
            else {
                // сообщение что не найден клиент с таким ИД в БД
                throw new ResponseException(String.format("Не найден клиент в БД с Id=%d!",id));
            }
        }
        else {
            throw new ResponseException("Отсутствует Id удаляемого клиента!");
        }
    }
}
