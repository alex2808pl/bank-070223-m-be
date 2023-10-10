package de.telran.bank.service;

import de.telran.bank.config.MapperUtil;
import de.telran.bank.dto.AccountDto;
import de.telran.bank.dto.TransactionDto;
import de.telran.bank.entity.Account;
import de.telran.bank.entity.Transaction;
import de.telran.bank.mapper.Mappers;
import de.telran.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private Mappers mappers;

    public List<AccountDto> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);

        List<AccountDto> accountList = MapperUtil.convertList(accounts, mappers::convertToAccountDto);

        return accountList;
    }
}
