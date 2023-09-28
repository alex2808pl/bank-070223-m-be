package de.telran.bank.entity;

import de.telran.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ManagerUnitJPATest {

    @Autowired
    private TestEntityManager entityManager;

    private Manager manager;

    private Client client;

    private Product product;


    @BeforeEach
    public void setUp() {
        manager = buildManager();
        client = buildClient((byte) 1);
        product = buildProduct();
    }
    @Test
    public void testManagerAdd() {
          Manager savedManager = this.entityManager.persistAndFlush(manager);
          Assertions.assertEquals(manager.getFirstName(), savedManager.getFirstName());
    }

    private Manager buildManager() {
        Manager manager = Manager.builder()
                .id(3L)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .status(ManagerStatus.ACTIVE)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        return manager;
    }

    private Client buildClient(byte number) {
        if (number==1) {
            return Client.builder()
                    .id(1L)
                    .firstName("Миронов")
                    .lastName("Игорь")
                    .email("test@test.com")
                    .phone("111111111111")
                    .address("Test Address")
                    .taxCode(1234567890L)
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();
        }
        return null;
    }

    private Product buildProduct() {
        return Product.builder()
                .id(1L)
                .name("Test Product")
                .currencyCode(302)
                .total(1000)
                .interestRate(1000)
                .status(ProductStatus.ACTIVE)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }
}