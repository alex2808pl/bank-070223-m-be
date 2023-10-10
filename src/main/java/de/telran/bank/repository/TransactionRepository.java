package de.telran.bank.repository;

import de.telran.bank.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
//    @Query("SELECT t FROM Transaction t JOIN Account a on t.debitAccount=a.id and t.creditAccount=a.id where t.id = ?1")

//    @Query("SELECT t FROM Transaction t where t.accounId = ?1")
//    List<Transaction> findByIdCreditAccount(Long accountId);
}
