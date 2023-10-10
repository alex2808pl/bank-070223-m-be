package de.telran.bank.mapper;

import de.telran.bank.dto.*;
import de.telran.bank.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mappers {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDto convertToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        transactionDto.setCreditAccountDto(convertToAccountDto(transaction.getCreditAccount()));
        transactionDto.setDebitAccountDto(convertToAccountDto(transaction.getDebitAccount()));
        return transactionDto;
    }

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
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setManagerDto(convertToManagerDto(product.getManager()));
        return productDto;
    }
    public ManagerDto convertToManagerDto(Manager manager) {
        ManagerDto managerDto = modelMapper.map(manager, ManagerDto.class);
        return managerDto;
    }
}
