package de.telran.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "manager_id")
//    private Long managerId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "currency_code")
    private int currencyCode;

    @Column(name = "interest_rate")
    private int interestRate;

    @Column(name = "total")
    private long total;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public Product() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Agreement> agreements = new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name = "manager_id")
//    public Manager getManager() {
//        return manager;
//    }
}
