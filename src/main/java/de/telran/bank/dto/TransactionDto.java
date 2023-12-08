package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.Account;
import de.telran.bank.entity.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class TransactionDto {

    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TransactionType type;
    private Long amount;
    private String description;
    private ZonedDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("debitAccount")
    AccountDto debitAccountDto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("creditAccount")
    AccountDto creditAccountDto;

//    private Account debitAccount;
//    private Account creditAccount;
}
