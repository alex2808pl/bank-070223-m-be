package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.Account;
import de.telran.bank.entity.ClientStatus;
import de.telran.bank.entity.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private ClientStatus status;
    private Long taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @JsonProperty("manager")
    ManagerDto managerDto;

//    @ManyToOne//(fetch = FetchType.LAZY)
//    private Manager manager;
//
//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    private Set<Account> accounts = new HashSet<>();
}
