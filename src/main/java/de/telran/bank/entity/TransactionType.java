package de.telran.bank.entity;

public enum TransactionType {
    DEBIT, CREDIT, OTHER
}

//    тип транзакции это не дебетовая и кредитовая.
//        транзакция всегда по умолчанию с одной стороны дебетовая с другой кредитовая,
//        там не правильные значения в енаме
