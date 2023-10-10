package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.Account;
import de.telran.bank.entity.AgreementStatus;
import de.telran.bank.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class AgreementDto {
    private Long id;
    private int interestRate;
    private AgreementStatus status;
    private long total;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @JsonProperty("product")
    ProductDto productDto;

//    @ManyToOne//(fetch = FetchType.LAZY)
//    private Product product;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    private Account account;
}
