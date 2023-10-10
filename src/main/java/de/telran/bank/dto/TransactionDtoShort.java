package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class TransactionDtoShort {

    private Long id;
    private TransactionType type;
    private Long amount;
    private String description;

    @JsonProperty("debitAccount")
    AccountDtoShort debitAccountDto;

    @JsonProperty("creditAccount")
    AccountDtoShort creditAccountDto;

//    private Account debitAccount;
//    private Account creditAccount;
}
