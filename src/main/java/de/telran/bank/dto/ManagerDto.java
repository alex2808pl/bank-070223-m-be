package de.telran.bank.dto;

import de.telran.bank.entity.Client;
import de.telran.bank.entity.ManagerStatus;
import de.telran.bank.entity.Product;
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
public class ManagerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private ManagerStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
//
//    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
//    private Set<Product> products = new HashSet<>();
//
//    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
//    private Set<Client> clients = new HashSet<>();

//    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval=true)
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
}
