package de.telran.bank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ManagerStatus status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
    public Manager() {
    }

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<Client> clients = new HashSet<>();

//    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval=true)
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
}
