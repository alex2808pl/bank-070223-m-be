package de.telran.bank.service;

import de.telran.bank.controller.advice.ResponseException;
import de.telran.bank.dto.ClientDto;
import de.telran.bank.dto.ManagerDto;
import de.telran.bank.entity.Client;
import de.telran.bank.entity.ClientStatus;
import de.telran.bank.entity.Manager;
import de.telran.bank.entity.ManagerStatus;
import de.telran.bank.mapper.Mappers;
import de.telran.bank.repository.ClientRepository;
import de.telran.bank.repository.ManagerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;
    @Mock
    private Mappers mappersMock;
    @Mock
    private ManagerRepository managerRepositoryMock;
    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private ClientService clientServiceTest;

    private Client expectedClient;

    private ClientDto expectedClientDto;

    private Manager expectedManager;

    @BeforeEach
    void init() {
        expectedClient = Client.builder()
                .id(1L)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .taxCode(12345678L)
                .phone("+4912341234567")
                .email("test@test.com")
                .address("Test address")
                .status(ClientStatus.BLOCKED)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        expectedClientDto = ClientDto.builder()
                .id(1L)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .taxCode(12345678L)
                .phone("+4912341234567")
                .email("test@test.com")
                .address("Test address")
                .status(ClientStatus.BLOCKED)
                .createdAt(expectedClient.getCreatedAt())
                .updatedAt(expectedClient.getUpdatedAt())
                .build();

        expectedManager = Manager.builder()
                .id(1L)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .status(ManagerStatus.INACTIVE)
                .createdAt(expectedClient.getCreatedAt())
                .updatedAt(expectedClient.getUpdatedAt())
                .build();
    }

    @Test
    void getClientIdTest() throws ResponseException {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedClient));
        when(mappersMock.convertToClientDto(any(Client.class))).thenReturn(expectedClientDto);

        ClientDto returnClientDto  = clientServiceTest.getClientId(1L);
        assertEquals(expectedClientDto, returnClientDto);
    }

    @Test
    void createClientTest() throws ResponseException {
        when(managerRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedManager));
        when(mappersMock.convertToClientEntity(any(ClientDto.class))).thenReturn(expectedClient);
        when(clientRepositoryMock.save(expectedClient)).thenReturn(expectedClient);
        when(mappersMock.convertToClientDto(any(Client.class))).thenReturn(expectedClientDto);

        ManagerDto expectedManagerDto = ManagerDto.builder()
                .id(expectedManager.getId())
                .firstName(expectedManager.getFirstName())
                .lastName(expectedManager.getLastName())
                .status(expectedManager.getStatus())
                .createdAt(expectedManager.getCreatedAt())
                .updatedAt(expectedManager.getUpdatedAt())
                .build();

        ClientDto expactedClientDtoWithOutId = ClientDto.builder()
                .firstName(expectedClient.getFirstName())
                .lastName(expectedClient.getLastName())
                .taxCode(expectedClient.getTaxCode())
                .phone(expectedClient.getPhone())
                .email(expectedClient.getEmail())
                .address(expectedClient.getAddress())
                .status(expectedClient.getStatus())
                .createdAt(expectedClient.getCreatedAt())
                .updatedAt(expectedClient.getUpdatedAt())
                .managerDto(expectedManagerDto)
                .build();

        ClientDto returnClientDto  = clientServiceTest.createClient(expactedClientDtoWithOutId);
        assertEquals(expectedClientDto, returnClientDto);
    }

    @Test
    @DisplayName("Тестируем обновление информации по клиенту")
    void updateClientTest() throws ResponseException {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedClient));
        when(clientRepositoryMock.save(expectedClient)).thenReturn(expectedClient);
        when(mappersMock.convertToClientDto(any(Client.class))).thenReturn(expectedClientDto);

        ClientDto returnClientDto  = clientServiceTest.updateClient(expectedClientDto);
        assertEquals(expectedClientDto, returnClientDto);
    }

    @Test
    void deleteClientTest() throws ResponseException {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedClient));
        when(clientRepositoryMock.save(expectedClient)).thenReturn(expectedClient);
        when(mappersMock.convertToClientDto(any(Client.class))).thenReturn(expectedClientDto);

        ClientDto returnClientDto  = clientServiceTest.deleteClient(1L);
        assertEquals(expectedClientDto, returnClientDto);
    }

    @Test
    void createClientExceptionTest() {
        assertThrows(ResponseException.class, () -> clientServiceTest.createClient(expectedClientDto));
    }

    @Test
    void getClientIdExceptionTest() {
        assertThrows(ResponseException.class, () -> clientServiceTest.getClientId(null));
    }

    @Test
    void updateClientExceptionTest() {
        expectedClientDto.setId(null);
        assertThrows(ResponseException.class, () -> clientServiceTest.updateClient(expectedClientDto));
    }

    @Test
    void deleteClientExceptionTest() {
        assertThrows(ResponseException.class, () -> clientServiceTest.deleteClient(null));
    }

    @Test
    void deleteClientExceptionNoSaveTest() {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedClient));
        when(clientRepositoryMock.save(expectedClient)).thenReturn(null);
        assertThrows(ResponseException.class, () -> clientServiceTest.deleteClient(1L));
    }
    @Test
    void deleteClientExceptionNoFindClientTest() {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResponseException.class, () -> clientServiceTest.deleteClient(1L));
    }
}