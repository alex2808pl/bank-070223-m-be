package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.AccountStatus;
import de.telran.bank.entity.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class AccountDtoShort {
    private String name;
    private int currencyCode;
}
