package de.telran.bank.mapper;

import de.telran.bank.dto.*;
import de.telran.bank.entity.*;
import de.telran.bank.security.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mappers {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDto convertToTransactionDto(Transaction transaction) {
//        modelMapper.typeMap(Transaction.class, TransactionDto.class)
//                .addMappings(mapper -> mapper.skip(TransactionDto::setType));
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);

        transactionDto.setCreditAccountDto(convertToAccountDto(transaction.getCreditAccount()));
        transactionDto.setDebitAccountDto(convertToAccountDto(transaction.getDebitAccount()));
        return transactionDto;
    }

    public TransactionDtoShort convertToTransactionDtoSkipType(Transaction transaction) {
        modelMapper.typeMap(Transaction.class, TransactionDtoShort.class)
                .addMappings(mapper -> mapper.skip(TransactionDtoShort::setType));
        TransactionDtoShort transactionDto = modelMapper.map(transaction, TransactionDtoShort.class);
        transactionDto.setCreditAccountDto(convertToAccountDtoWithoutRelation(transaction.getCreditAccount()));
        transactionDto.setDebitAccountDto(convertToAccountDtoWithoutRelation(transaction.getDebitAccount()));
        return transactionDto;
    }

//    private User convertToNoPassUser(User user) {
//        modelMapper.typeMap(User.class, User.class).addMappings(mapper -> mapper.skip(User::setPassword));
//        return modelMapper.map(user, User.class);
//    }

    public TransactionDtoShort convertToTransactionDtoWithoutRelation(Transaction transaction) {
        TransactionDtoShort transactionDto = modelMapper.map(transaction, TransactionDtoShort.class);
        transactionDto.setCreditAccountDto(convertToAccountDtoWithoutRelation(transaction.getCreditAccount()));
        transactionDto.setDebitAccountDto(convertToAccountDtoWithoutRelation(transaction.getDebitAccount()));
        return transactionDto;
    }

    public AccountDtoShort convertToAccountDtoWithoutRelation(Account account) {
        AccountDtoShort accountDto = modelMapper.map(account, AccountDtoShort.class);
//        accountDto.setAgreementDto(convertToAgreementDto(account.getAgreement()));
//        accountDto.setClientDto(convertToClientDto(account.getClient()));
        return accountDto;
    }

    public AccountDto convertToAccountDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setAgreementDto(convertToAgreementDto(account.getAgreement()));
        accountDto.setClientDto(convertToClientDto(account.getClient()));
        return accountDto;
    }

    public AgreementDto convertToAgreementDto(Agreement agreement) {
        AgreementDto agreementDto = modelMapper.map(agreement, AgreementDto.class);
        agreementDto.setProductDto(convertToProductDto(agreement.getProduct()));
        return agreementDto;
    }

    public ClientDto convertToClientDto(Client client) {
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        clientDto.setManagerDto(convertToManagerDto(client.getManager()));
        return clientDto;
    }

    public Client convertToClientEntity(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        return client;
    }

    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setManagerDto(convertToManagerDto(product.getManager()));
        return productDto;
    }
    public ManagerDto convertToManagerDto(Manager manager) {
        ManagerDto managerDto = modelMapper.map(manager, ManagerDto.class);
        return managerDto;
    }

    public Transaction convertToTransactionEntity(TransactionDto transactionDto) {
        Transaction transaction  = modelMapper.map(transactionDto, Transaction.class);
        return transaction;
    }
}
