package de.telran.bank.entity;

import de.telran.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;

class ManagerUnitTest {

    private static SessionFactory sessionFactory;
    private Session session;
    private org.hibernate.Transaction transaction;

    @BeforeAll
    public static void beforeTests() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    public void setUp() {
        session = sessionFactory.openSession();
//        transaction = session.beginTransaction();
    }

    @AfterEach
    public void tearDown() {
//        transaction.commit();//.rollback();
        session.close();
    }

    @Test
    public void testManagerAdd() {
        transaction = session.beginTransaction();
        Manager testManager = buildManager();
        testManager.getProducts().add(buildProduct());
        testManager.getClients().add(buildClient(1));
        Long idReturn = (Long) session.save(testManager);
        session.flush();
        transaction.commit();
//
        Manager testManagerFind = session.find(Manager.class, idReturn);
        Assertions.assertTrue(Objects.nonNull(testManagerFind));

        transaction = session.beginTransaction();
        session.remove(testManagerFind);
        session.flush();
        transaction.commit();

    }

    private Manager buildManager() {
        Manager manager = Manager.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .status(ManagerStatus.ACTIVE)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .products(new HashSet<>())
                .clients(new HashSet<>())
                .build();
        return manager;
    }

    private Client buildClient(int number) {
        if (number==1) {
            return Client.builder()
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