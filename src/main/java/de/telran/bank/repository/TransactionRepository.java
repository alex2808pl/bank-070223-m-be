package de.telran.bank.repository;

import de.telran.bank.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t JOIN t.debitAccount da JOIN da.client c WHERE c.id=?1")
    List<Transaction> findByClientId(Long clientId);

    @Query("SELECT t FROM Transaction t JOIN t.debitAccount da JOIN da.client c JOIN c.manager m " +
            "WHERE m.lastName LIKE %?1%")
    List<Transaction> findTransactionsByManagerId(String name);
}
