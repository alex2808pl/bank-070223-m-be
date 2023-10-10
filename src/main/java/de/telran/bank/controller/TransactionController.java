package de.telran.bank.controller;

import de.telran.bank.dto.TransactionDto;
import de.telran.bank.dto.TransactionDtoShort;
import de.telran.bank.entity.Transaction;
import de.telran.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransactions() {
        List<TransactionDto> transactions = transactionService.getTransactionsMapper();//getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionDtoShort> getTransactionsId(@PathVariable Long id) {
        TransactionDtoShort transaction = transactionService.getTransactionId(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
