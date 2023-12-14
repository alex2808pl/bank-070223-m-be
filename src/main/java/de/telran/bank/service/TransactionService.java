package de.telran.bank.service;

import de.telran.bank.config.MapperUtil;
import de.telran.bank.controller.advice.ResponseException;
import de.telran.bank.dto.*;
import de.telran.bank.entity.*;
import de.telran.bank.mapper.Mappers;
import de.telran.bank.repository.AccountRepository;
import de.telran.bank.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    public List<TransactionDto> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);

        List<TransactionDto> transactionList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = new TransactionDto();
            BeanUtils.copyProperties(transaction, transactionDto);
            transactionList.add(transactionDto);
        }
        return transactionList;
    }

    @Autowired
    private Mappers mappers;

    public List<TransactionDto> getTransactionsMapper() {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);

        List<TransactionDto> transactionList = MapperUtil.convertList(transactions, mappers::convertToTransactionDto);

        return transactionList;
    }

    public TransactionDtoShort getTransactionIdShort(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        TransactionDtoShort transactionDto = MapperUtil.convertList(List.of(transaction), mappers::convertToTransactionDtoWithoutRelation).get(0);
//        TransactionDtoShort transactionDto = MapperUtil.convertList(List.of(transaction), mappers::convertToTransactionDtoSkipType).get(0);

        return transactionDto;

    }

    public TransactionDto getTransactionId(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        TransactionDto transactionDto = MapperUtil.convertList(List.of(transaction), mappers::convertToTransactionDto).get(0);

        return transactionDto;

    }

    public List<TransactionDto> getTransactionsByClientId(Long id) {
        List<Transaction> transactions = transactionRepository.findByClientId(id);
        List<TransactionDto> transactionList = MapperUtil.convertList(transactions, mappers::convertToTransactionDto);
        return transactionList;
    }

    public List<TransactionDto> findTransactionsByManagerId(String name) {
        List<Transaction> transactions = transactionRepository.findTransactionsByManagerId(name);
        List<TransactionDto> transactionList = MapperUtil.convertList(transactions, mappers::convertToTransactionDto);
        return transactionList;
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) throws ResponseException {
        AccountDto accountDebitDto = transactionDto.getDebitAccountDto();
        Optional<Account> accountDebit = accountRepository.findById(accountDebitDto.getId());
        AccountDto accountCreditDto = transactionDto.getCreditAccountDto();
        Optional<Account> accountCredit = accountRepository.findById(accountCreditDto.getId());
        if(accountDebit.isPresent() && accountCredit.isPresent()) {
            Transaction transaction = mappers.convertToTransactionEntity(transactionDto);
            transaction.setDebitAccount(accountDebit.get());
            transaction.setCreditAccount(accountCredit.get());
            transaction.setCreatedAt(ZonedDateTime.now());
            Transaction transactionResponse = transactionRepository.save(transaction);
            Long idResponse = transactionResponse.getId();
            if(idResponse != null && idResponse > 0) {
                return mappers.convertToTransactionDto(transactionResponse);
            }
            else {
                // сообщение что создать транзакцию не удалось
                throw new ResponseException("Не удалось создать транзакцию в БД");
            }
        }
        return null;
    }
}
