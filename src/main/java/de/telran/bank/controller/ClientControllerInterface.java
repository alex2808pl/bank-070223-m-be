package de.telran.bank.controller;

import de.telran.bank.controller.advice.ResponseException;
import de.telran.bank.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name="Контроллер клиентов", description="Контроллер для работы с клиентами")
public interface ClientControllerInterface {

    @Operation(
            summary = "Получить информацию о клиенте",
            description = "Позволяет информация о клиенте по его Id"
    )
    ResponseEntity<ClientDto> getClientId(@PathVariable Long id) throws ResponseException;

    @Operation(
            summary = "Создание клиента",
            description = "Создать нового клиента"
    )
    ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) throws ResponseException;


}
