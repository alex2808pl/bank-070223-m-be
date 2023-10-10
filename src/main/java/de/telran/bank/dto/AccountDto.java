package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String name;
    private AccountType type;
    private AccountStatus status;
    private long balance;
    private int currencyCode;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @JsonProperty("agreement")
    AgreementDto agreementDto;

    @JsonProperty("client")
    ClientDto clientDto;


//    private Client client;
//    private Set<Transaction> debitTransactions = new HashSet<>();
//    private Set<Transaction> creditTransactions = new HashSet<>();
//    private Agreement agreement;
}
