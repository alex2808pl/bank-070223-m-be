package de.telran.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.bank.entity.Agreement;
import de.telran.bank.entity.Manager;
import de.telran.bank.entity.ProductStatus;
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
public class ProductDto {
    private Long id;
    private String name;
    private ProductStatus status;
    private int currencyCode;
    private int interestRate;
    private long total;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @JsonProperty("manager")
    ManagerDto managerDto;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Manager manager;
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private Set<Agreement> agreements = new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name = "manager_id")
//    public Manager getManager() {
//        return manager;
//    }
}
