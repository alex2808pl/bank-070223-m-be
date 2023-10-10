package de.telran.bank.service;

import de.telran.bank.config.MapperUtil;
import de.telran.bank.dto.*;
import de.telran.bank.entity.*;
import de.telran.bank.mapper.Mappers;
import de.telran.bank.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

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


    public TransactionDtoShort getTransactionId(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        TransactionDtoShort transactionDto = MapperUtil.convertList(List.of(transaction), mappers::convertToTransactionDtoWithoutRelation).get(0);

        return transactionDto;

    }
}
