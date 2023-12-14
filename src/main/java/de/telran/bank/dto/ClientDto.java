package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.Account;
import de.telran.bank.entity.ClientStatus;
import de.telran.bank.entity.Manager;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private Long id;

    private ClientStatus status;
    private Long taxCode;

    @NotBlank(message = "Invalid firstName: Empty name")
//    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid firstName: Must be of 3 - 30 characters")
    private String firstName;

    @NotBlank(message = "Invalid lastName: Empty name")
//    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid lastName: Must be of 3 - 30 characters")
    private String lastName;

    @Email(message = "Invalid email")
    private String email;
    private String address;

    @NotBlank(message = "Invalid Phone number: Empty number")
//    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{12}$", message = "Invalid phone number")
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
