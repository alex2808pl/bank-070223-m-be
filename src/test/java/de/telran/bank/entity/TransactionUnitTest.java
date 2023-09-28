package de.telran.bank.entity;

import de.telran.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;

class TransactionUnitTest {

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
        transaction = session.beginTransaction();
    }

    @AfterEach
    public void tearDown() {
        transaction.commit();//.rollback();
        session.close();
    }

//    @Test
    public void testTransactionAdd() {
        Account accountDebit = Account.builder()
                .id(Long.MAX_VALUE)
                .name("11111111111111111111")
                .balance(1000)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        Account accountCredit = Account.builder()
                .id(Long.MAX_VALUE-1)
                .name("2222222222222222222")
                .balance(1000)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        Long idDebit = (Long) session.save(accountDebit);
        Long idCredit = (Long) session.save(accountCredit);


//        Account accountDebit = session.get(Account.class, 1);
//        Account accountCredit = session.get(Account.class, 2);
//
        Transaction testTransaction = Transaction.builder()
                .id(Long.MAX_VALUE)
                .creditAccount(accountCredit)
                .debitAccount(accountDebit)
                .type(TransactionType.DEBIT)
                .amount(333L)
                .description("Test transaction")
                .createdAt(ZonedDateTime.now())
                .build();
        session.persist(testTransaction);

//        session.merge(testTransaction);
        session.flush();

        Transaction testTransactionFind = session.find(Transaction.class, Long.MAX_VALUE);
//        Assertions.assertTrue(Objects.nonNull(testManagerFind));
//        session.saveOrUpdate(testManager);
//        session.flush();

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

}