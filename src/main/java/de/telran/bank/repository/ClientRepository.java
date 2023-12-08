package de.telran.bank.repository;

import de.telran.bank.entity.Account;
import de.telran.bank.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository  extends CrudRepository<Client,Long> {
    @Query("SELECT DISTINCT c FROM Client c JOIN c.accounts a JOIN a.debitTransactions dt"
            + " WHERE c.id=?1")
    public Client findClientById(Long id);

}
